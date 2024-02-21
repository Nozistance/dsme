package io.nozistance.dsme.service.impl;

import io.nozistance.dsme.model.Item;
import io.nozistance.dsme.service.DataSupplierService;
import io.nozistance.dsme.util.DayOfWeek;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

import java.util.EnumSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class DocumentDataSupplierService implements DataSupplierService {

    private final DocumentFetchingService documentFetchingService;
    private final DocumentParsingService documentParsingService;

    @Override
    public List<Item> getFor(DayOfWeek dayOfWeek) {
        Map<String, Item> buffer = new HashMap<>();
        List<Item> items = getItems(dayOfWeek);
        items.forEach(i -> merge(buffer, i));
        return List.copyOf(buffer.values());
    }

    private List<Item> getItems(DayOfWeek day) {
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
