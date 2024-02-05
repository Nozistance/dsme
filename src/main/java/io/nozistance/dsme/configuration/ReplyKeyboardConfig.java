package io.nozistance.dsme.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.List;

import static io.nozistance.dsme.util.DayOfWeek.*;

@Configuration
@AllArgsConstructor
public class ReplyKeyboardConfig {

    private final TextProperties textProperties;

    @Bean
    public ReplyKeyboard dayOfWeekButtons() {
        return new ReplyKeyboardMarkup(List.of(
                new KeyboardRow(List.of(
                        new KeyboardButton(MONDAY.getDay()),
                        new KeyboardButton(TUESDAY.getDay())
                )),
                new KeyboardRow(List.of(
                        new KeyboardButton(WEDNESDAY.getDay()),
                        new KeyboardButton(THURSDAY.getDay())
                )),
                new KeyboardRow(List.of(
                        new KeyboardButton(FRIDAY.getDay()),
                        new KeyboardButton(SATURDAY.getDay())
                )),
                new KeyboardRow(List.of(
                        new KeyboardButton(SUNDAY.getDay()),
                        new KeyboardButton(textProperties.getKeyboardCancel())
                ))
        ));
    }
}
