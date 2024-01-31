package io.nozistance.dsme.service;

import io.nozistance.dsme.entity.Item;
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

@Slf4j
@Service
@AllArgsConstructor
public class DataUpdatingService {

    private final DataFetchingService dataFetchingService;
    private final DataParsingService dataParsingService;
    private final MenuRepository menuRepository;
    private final Map<DayOfWeek, URI> uris;

    @Transactional
    @Scheduled(cron = "${update-frequency}")
    public void updateData() {
        menuRepository.deleteAll();
        uris.forEach((day, uri) -> {
            List<Item> items = getItems(uri);
            saveDailyMenu(day, items);
        });
    }

    private List<Item> getItems(URI uri) {
        Document document = dataFetchingService.getDocument(uri);
        return dataParsingService.parse(document);
    }

    private void saveDailyMenu(DayOfWeek day, List<Item> items) {
        items.forEach(item -> menuRepository
                .findByName(item.getName())
                .ifPresentOrElse(e -> {
                    e.getDaysOfWeek().add(day);
                    menuRepository.saveAndFlush(e);
                }, () -> {
                    item.getDaysOfWeek().clear();
                    item.getDaysOfWeek().add(day);
                    menuRepository.saveAndFlush(item);
                }));
    }
}
