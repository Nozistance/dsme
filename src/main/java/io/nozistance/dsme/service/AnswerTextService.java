package io.nozistance.dsme.service;

import lombok.RequiredArgsConstructor;

import java.util.Map;

@RequiredArgsConstructor
public class AnswerTextService {

    private final Map<String, String> answers;

    public String getAnswer(String key, Object... args) {
        return args == null
                ? answers.get(key)
                : answers.get(key)
                .formatted(args);
    }
}
