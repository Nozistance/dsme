package io.nozistance.dsme.service;

import io.nozistance.dsme.model.Item;
import lombok.RequiredArgsConstructor;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class DataParsingService {

    private static final String FILTER_SELECTOR = "div.menu__filter > span";
    private static final String DISHES_SELECTOR = "div.assortment__item.dishes";
    private static final String INFORMATION_SELECTOR = "div.assortment__item-title.assortment__item-title--name.itemLinkModal";

    public List<Item> parse(Document document) {
        Map<String, String> categories = extractCategories(document);
        return extractDishes(document, categories);
    }

    private Map<String, String> extractCategories(Document document) {
        return document.select(FILTER_SELECTOR).stream()
                .collect(Collectors.toMap(
                        e -> e.attr("data-section"),
                        Element::text,
                        (existing, replacement) -> replacement
                ));
    }

    private List<Item> extractDishes(Document document, Map<String, String> categories) {
        return document.select(DISHES_SELECTOR).stream()
                .flatMap(dish -> extractMenuItem(categories, dish))
                .map(this::parseMenuItem)
                .toList();
    }

    private Stream<Pair<String, Element>> extractMenuItem(Map<String, String> categories, Element dishElement) {
        String category = categories.get(dishElement.attr("data-section"));
        return dishElement.select(INFORMATION_SELECTOR).stream()
                .map(infoElement -> Pair.of(category, infoElement));
    }

    private Item parseMenuItem(Pair<String, Element> menuItem) {
        Element element = menuItem.getSecond();
        return Item.builder()
                .category(menuItem.getFirst())
                .name(element.text())
                .price(element.attr("data-price"))
                .weight(element.attr("data-gramm"))
                .image(element.attr("data-image"))
                .calories(element.attr("data-kkal"))
                .composition(cleanComposition(element.attr("data-text")))
                .build();
    }

    private String cleanComposition(String composition) {
        return composition.replaceAll("<[^>]+>|Состав:|(\u00a0|КБЖУ).*", "")
                .trim();
    }
}
