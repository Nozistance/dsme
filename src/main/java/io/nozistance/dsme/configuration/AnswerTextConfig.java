package io.nozistance.dsme.configuration;

import io.nozistance.dsme.properties.AnswerTextProperties;
import io.nozistance.dsme.service.AnswerTextService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class AnswerTextConfig {

    private final AnswerTextProperties answerText;

    @Bean
    public AnswerTextService answers() {
        var answers = answerText.getAnswers();
        return new AnswerTextService(answers);
    }
}
