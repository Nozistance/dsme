package io.nozistance.dsme.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;

import java.util.EnumSet;
import java.util.stream.IntStream;

import static io.nozistance.dsme.util.DayOfWeek.values;
import static java.lang.Math.min;
import static org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton.builder;

@Configuration
@AllArgsConstructor
public class ReplyKeyboardConfig {

    @Bean
    public ReplyKeyboard dayOfWeekButtons() {
        return new InlineKeyboardMarkup(IntStream
                .rangeClosed(0, (int) ((values().length - 0.5f) / 2))
                .mapToObj(i -> EnumSet.of(
                        values()[min(i * 2, values().length - 1)],
                        values()[min(i * 2 + 1, values().length - 1)]
                ))
                .map(e -> e.stream().map(d -> builder()
                        .callbackData("day:" + d.getDay())
                        .text(d.getDay()).build())
                        .toList())
                .toList()
        );
    }
}
