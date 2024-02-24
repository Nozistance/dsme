package io.nozistance.dsme.service.impl;

import io.nozistance.dsme.model.Item;
import io.nozistance.dsme.util.DayOfWeek;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.EnumSet;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DocumentParsingServiceTest {

    @InjectMocks
    private DocumentParsingService documentParsingService;

    @Test
    void parseTest() throws IOException {
        Resource resource = new ClassPathResource("parsing-test.html");
        String html = resource.getContentAsString(Charset.defaultCharset());

        Document document = Jsoup.parse(html);
        List<Item> items = documentParsingService.parse(document);

        assertNotNull(items);
        assertEquals(2, items.size());
        assertThat(items.get(0)).usingRecursiveComparison()
                .isEqualTo(new Item(null, "Classic Breakfast",
                        "Breakfast", "Eggs, Toast, Bacon",
                        "100g", "200", "10", "image1.jpg",
                        EnumSet.of(DayOfWeek.MONDAY)));
        assertThat(items.get(1)).usingRecursiveComparison()
                .isEqualTo(new Item(null, "Healthy Lunch",
                        "Lunch", "Chicken, Rice, Vegetables",
                        "250g", "500", "15", "image2.jpg",
                        EnumSet.of(DayOfWeek.MONDAY)));
    }
}
