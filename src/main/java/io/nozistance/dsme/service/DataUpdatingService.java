package io.nozistance.dsme.service;

import io.nozistance.dsme.model.Item;
import io.nozistance.dsme.repository.MenuRepository;
import io.nozistance.dsme.util.DayOfWeek;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@AllArgsConstructor
public class DataUpdatingService {

     private final DocumentFetchingService documentFetchingService;
    private final DocumentParsingService documentParsingService;
    private final MenuRepository menuRepository;
    private final CacheService cacheService;
    private final Map<DayOfWeek, URI> uris;

    @Transactional
    @Scheduled(cron = "${data-updating.update-frequency}")
    public void update() {
        cacheService.evictAllCaches();
        menuRepository.deleteAllInBatch();
        Map<String, Item> buffer = new ConcurrentHashMap<>();
        uris.entrySet().parallelStream().forEach(e -> {
            List<Item> items = getItems(e.getValue());
            mergeDailyMenu(buffer, e.getKey(), items);
        });
        menuRepository.saveAll(buffer.values());
    }

    private List<Item> getItems(URI uri) {
        Document document = documentFetchingService.getDocument(uri);
        return documentParsingService.parse(document);
    }

    private void mergeDailyMenu(Map<String, Item> records, DayOfWeek day, List<Item> items) {
        items.forEach(item -> records.merge(item.getName(), item, (existing, newItem) -> {
            existing.getDaysOfWeek().add(day);
            return existing;
        }));
    }
}
