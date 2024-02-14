package io.nozistance.dsme.configuration;

import io.nozistance.dsme.properties.TextProperties;
import io.nozistance.dsme.service.AnswerTextService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class TextConfig {

    private final TextProperties textProperties;

    @Bean
    public AnswerTextService answers() {
        var answers = textProperties.getAnswers();
        return new AnswerTextService(answers);
    }
}
