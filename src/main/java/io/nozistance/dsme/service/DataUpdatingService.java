package io.nozistance.dsme.service;

import io.nozistance.dsme.model.Item;
import io.nozistance.dsme.repository.MenuRepository;
import io.nozistance.dsme.util.DayOfWeek;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jsoup.nodes.Document;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
@AllArgsConstructor
public class DataUpdatingService {

    private final DataFetchingService dataFetchingService;
    private final DataParsingService dataParsingService;
    private final MenuRepository menuRepository;
    private final Map<DayOfWeek, URI> uris;

    @Scheduled(cron = "${update-frequency}")
    @EventListener(ApplicationStartedEvent.class)
    public void updateData() {
        menuRepository.deleteAll();
        Map<String, Item> map = new ConcurrentHashMap<>();
        uris.entrySet().parallelStream().forEach(e -> {
            List<Item> items = getItems(e.getValue());
            mergeDailyMenu(map, e.getKey(), items);
        });
        menuRepository.saveAll(map.values());
    }

    private List<Item> getItems(URI uri) {
        Document document = dataFetchingService.getDocument(uri);
        return dataParsingService.parse(document);
    }

    private void mergeDailyMenu(Map<String, Item> records,
                                DayOfWeek day, List<Item> items) {
        for (Item item : items) {
            Item existing = records.get(item.getName());
            if (existing != null) {
                existing.getDaysOfWeek().add(day);
                records.put(existing.getName(), existing);
            } else {
                item.getDaysOfWeek().clear();
                item.getDaysOfWeek().add(day);
                records.put(item.getName(), item);
            }
        }
    }
}
