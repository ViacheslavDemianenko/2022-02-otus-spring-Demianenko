package ru.otus.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class Question {
    private final int id;
    private final String questionText;

    @Override
    public String toString() {
        return "Question №" + id + ": " + questionText;
    }
}
