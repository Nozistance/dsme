package io.nozistance.dsme.service;

import org.springframework.data.util.Pair;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class KeyboardService {

    public ReplyKeyboard singleColumn(List<Pair<String, String>> list) {
        return new InlineKeyboardMarkup(list.stream()
                .map(this::toButton)
                .map(List::of)
                .toList());
    }

    public ReplyKeyboard doubleColumn(List<Pair<String, String>> list) {
        return new InlineKeyboardMarkup(
                groupToPairs(list.stream()
                        .map(this::toButton)
                        .toList())
        );
    }

    private InlineKeyboardButton toButton(Pair<String, String> pair) {
        return InlineKeyboardButton.builder()
                .callbackData(pair.getSecond())
                .text(pair.getFirst())
                .build();
    }

    private <T> List<List<T>> groupToPairs(List<T> list) {
        return IntStream.iterate(0, i -> i < list.size(), i -> i + 2)
                .mapToObj(i -> list.subList(i, Math.min(i + 2, list.size())))
                .toList();
    }
}
