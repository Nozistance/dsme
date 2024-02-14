package io.nozistance.dsme.service;

import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.Objects;

@RequiredArgsConstructor
public class AnswerTextService {

    private final Map<String, String> answers;

    public String getAnswer(String key, Object... args) {
        return answers.get(key)
                .formatted(args == null
                        ? new Objects[]{}
                        : args);
    }
}
