package io.nozistance.dsme.properties;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Map;

@Getter
@RequiredArgsConstructor
@ConfigurationProperties("text")
public class AnswerTextProperties {

    private final Map<String, String> answers;
}
