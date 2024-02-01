package io.nozistance.dsme.service;

import io.nozistance.dsme.entity.Item;
import lombok.AllArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.data.util.Pair.of;

@Service
@AllArgsConstructor
public class DataParsingService {

    private static final String FILTER = "div.menu__filter > span";
    private static final String DISHES = "div.assortment__item.dishes";
    private static final String INFORMATION = "div.assortment__item-title.assortment__item-title--name.itemLinkModal";

    public List<Item> parse(Document document) {
        Map<String, String> categories = extractCategories(document);
        return document.select(DISHES).stream()
                .flatMap(e -> extractMenuItem(categories, e))
                .map(this::parseMenuItem).toList();
    }

    private Map<String, String> extractCategories(Document document) {
        return document.select(FILTER).stream()
                .collect(Collectors.toMap(
                        e -> e.attr("data-section"),
                        Element::text,
                        (x, y) -> y
                ));
    }

    private Stream<Pair<String, Element>> extractMenuItem(Map<String, String> categories, Element element) {
        String category = categories.get(element.attr("data-section"));
        return element.select(INFORMATION).stream()
                .map(infoElement -> of(category, infoElement));
    }

    private Item parseMenuItem(Pair<String, Element> categoryAndData) {
        String category = categoryAndData.getFirst();
        Element element = categoryAndData.getSecond();
        Item item = new Item();
        item.setCategory(category);
        item.setName(element.text());
        item.setPrice(element.attr("data-price"));
        item.setWeight(element.attr("data-gramm"));
        item.setImage(element.attr("data-image"));
        item.setCalories(element.attr("data-kkal"));
        item.setComposition(element.attr("data-text")
                .replaceAll("<[^>]++>|Состав:", "").trim());
        return item;
    }
}
