package io.nozistance.dsme.configuration;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboard;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.List;

import static io.nozistance.dsme.util.DayOfWeek.*;

@Configuration
@AllArgsConstructor
public class ReplyKeyboardConfig {

    private final TextProperties textProperties;

    @Bean
    public ReplyKeyboard dayOfWeekButtons() {
        return new InlineKeyboardMarkup(List.of(
                List.of(new InlineKeyboardButton(MONDAY.getDay()),
                        new InlineKeyboardButton(TUESDAY.getDay())),
                List.of(new InlineKeyboardButton(WEDNESDAY.getDay()),
                        new InlineKeyboardButton(THURSDAY.getDay())),
                List.of(new InlineKeyboardButton(FRIDAY.getDay()),
                        new InlineKeyboardButton(SATURDAY.getDay())),
                List.of(new InlineKeyboardButton(SUNDAY.getDay()),
                        new InlineKeyboardButton(textProperties.getKeyboardCancel()))
        ));
    }
}
