package io.nozistance.dsme.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.util.Pair;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(MockitoExtension.class)
class KeyboardServiceTest {

    @InjectMocks
    private KeyboardService keyboardService;

    @Test
    void testSingleColumn() {
        List<Pair<String, String>> pairs = List.of(
                Pair.of("Option 1", "Callback 1"),
                Pair.of("Option 2", "Callback 2")
        );

        InlineKeyboardMarkup result = (InlineKeyboardMarkup) keyboardService.singleColumn(pairs);

        assertEquals(pairs.size(), result.getKeyboard().size());
        for (int i = 0; i < pairs.size(); i++) {
            InlineKeyboardButton button = result.getKeyboard().get(i).get(0);
            assertEquals(pairs.get(i).getFirst(), button.getText());
            assertEquals(pairs.get(i).getSecond(), button.getCallbackData());
        }
    }

    @Test
    void testDoubleColumn() {
        List<Pair<String, String>> pairs = List.of(
                Pair.of("Option 1", "Callback 1"),
                Pair.of("Option 2", "Callback 2"),
                Pair.of("Option 3", "Callback 3")
        );
        int expectedRows = (int) Math.ceil(pairs.size() / 2.0);

        InlineKeyboardMarkup result = (InlineKeyboardMarkup) keyboardService.doubleColumn(pairs);

        assertEquals(expectedRows, result.getKeyboard().size());
        assertEquals(1, result.getKeyboard().get(result.getKeyboard().size() - 1).size());
        assertTrue(result.getKeyboard().stream()
                .flatMap(List::stream)
                .allMatch(button -> pairs.stream()
                        .anyMatch(pair -> button.getText().equals(pair.getFirst()) && button.getCallbackData().equals(pair.getSecond()))));
    }
}