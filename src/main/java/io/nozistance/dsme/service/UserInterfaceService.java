package io.nozistance.dsme.service;

import io.nozistance.dsme.entity.Item;
import io.nozistance.dsme.util.DayOfWeek;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.EnumSet;
import java.util.stream.IntStream;

import static io.nozistance.dsme.util.DayOfWeek.values;
import static java.lang.Math.min;
import static org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton.builder;

@Service
public class UserInterfaceService {

    public InlineKeyboardButton button(Item item) {
        return builder()
                .callbackData("item:" + item.getId())
                .text("%s | %s".formatted(
                        item.getName(),
                        item.getPrice()
                )).build();
    }

    public InlineKeyboardButton button(DayOfWeek day) {
        return builder()
                .callbackData("day:" + day)
                .text(day.toString())
                .build();
    }

    public ReplyKeyboard dayOfWeekKeyboard() {
        return new InlineKeyboardMarkup(IntStream
                .rangeClosed(0, (int) ((values().length - 0.5f) / 2))
                .mapToObj(i -> EnumSet.of(
                        values()[min(i * 2, values().length - 1)],
                        values()[min(i * 2 + 1, values().length - 1)]
                )).map(e -> e.stream().map(this::button).toList()).toList()
        );
    }
}
