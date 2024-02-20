package io.nozistance.dsme.service;

import io.nozistance.dsme.model.Item;
import io.nozistance.dsme.util.DayOfWeek;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.EnumSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DocumentParsingServiceTest {

    @InjectMocks
    private DocumentParsingService documentParsingService;

    @Test
    void parseTest() {
        String html = "<html><body><div class=\"menu__filter\"><span data-section=\"test\">Category</span></div><div class=\"assortment__item dishes\" data-section=\"test\"><div class=\"assortment__item-title assortment__item-title--name itemLinkModal\" data-price=\"10\" data-gramm=\"100\" data-image=\"image.jpg\" data-kkal=\"200\" data-text=\"Composition\">Item Name</div></div></body></html>";
        Document document = Jsoup.parse(html);
        List<Item> items = documentParsingService.parse(document);
        assertNotNull(items);
        assertFalse(items.isEmpty());
        assertThat(items.get(0)).usingRecursiveComparison()
                .isEqualTo(new Item(null,
                        "Item Name", "Category", "Composition",
                        "100", "200", "10", "image.jpg",
                        EnumSet.noneOf(DayOfWeek.class)
                ));
    }
}
