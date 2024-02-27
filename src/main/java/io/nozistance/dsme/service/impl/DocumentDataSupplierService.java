package io.nozistance.dsme.service.impl;

import io.nozistance.dsme.entity.Item;
import io.nozistance.dsme.service.DataSupplierService;
import io.nozistance.dsme.util.DayOfWeek;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
public class DocumentDataSupplierService implements DataSupplierService {

    private final DocumentFetchingService documentFetchingService;
    private final DocumentParsingService documentParsingService;

    @Override
    public List<Item> get() {
        var buffer = new ConcurrentHashMap<String, Item>();
        Arrays.stream(DayOfWeek.values()).parallel()
                .map(this::get).flatMap(List::stream)
                .forEach(i -> merge(buffer, i));
        return List.copyOf(buffer.values());
    }

    private List<Item> get(DayOfWeek day) {
        Document document = documentFetchingService.getDocument(day);
        return documentParsingService.parse(document);
    }

    private void merge(Map<String, Item> buffer, Item item) {
        buffer.merge(item.getName(), item, (existing, newItem) -> {
            var set = EnumSet.copyOf(newItem.getDaysOfWeek());
            set.addAll(existing.getDaysOfWeek());
            existing.setDaysOfWeek(set);
            return existing;
        });
    }
}
