package io.nozistance.dsme.service.impl;

import io.nozistance.dsme.model.Item;
import io.nozistance.dsme.util.DayOfWeek;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static io.nozistance.dsme.util.DayOfWeek.byName;

@Service
public class DocumentParsingService {

    private static final String DAY_SELECTOR = "div.info-header__filter > span.info-header__filter-item.info-header__filter-item--active";
    private static final String ITEMS_SELECTOR = "div.assortment__item.dishes";
    private static final String CATEGORIES_SELECTOR = "div.menu__filter > span";
    private static final String INFORMATION_SELECTOR = "div.assortment__item-title.assortment__item-title--name";

    public List<Item> parse(Document document) {
        var categories = parseCategories(document);
        DayOfWeek dayOfWeek = parseDayOfWeek(document);
        return parseItems(document, categories, dayOfWeek);
    }

    private Map<String, String> parseCategories(Document document) {
        return document.select(CATEGORIES_SELECTOR)
                .stream().collect(Collectors.toMap(
                        e -> e.attr("data-section"),
                        Element::text, (x, y) -> y
                ));
    }

    private DayOfWeek parseDayOfWeek(Document document) {
        Elements elements = document.select(DAY_SELECTOR);
        return byName(elements.text());
    }

    private List<Item> parseItems(Document document, Map<String, String> categories, DayOfWeek day) {
        return document.select(ITEMS_SELECTOR).stream()
                .map(e -> compound(e, categories, day))
                .map(this::from)
                .toList();
    }

    private Element compound(Element element, Map<String, String> categories, DayOfWeek day) {
        Elements info = element.select(INFORMATION_SELECTOR);
        info = info.attr("data-day", day.toString());
        String category = element.attr("data-section");
        info = info.attr("data-section", categories.get(category));
        info = info.attr("data-text", info.attr("data-text")
                .replaceAll("<[^>]+>|Состав:|КБЖУ.*", "")
                .replaceAll(" {2,}", " ")
                .trim());
        return info.first();
    }

    private Item from(Element info) {
        return Item.builder()
                .name(info.text())
                .price(info.attr("data-price"))
                .image(info.attr("data-image"))
                .weight(info.attr("data-gramm"))
                .calories(info.attr("data-kkal"))
                .category(info.attr("data-section"))
                .ingredients(info.attr("data-text"))
                .dayOfWeek(byName(info.attr("data-day")))
                .build();
    }
}
