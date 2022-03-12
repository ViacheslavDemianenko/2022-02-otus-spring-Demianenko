package ru.otus.service;

import ru.otus.model.Test;

public interface TestService {

    Test startTest();

    void checkNumberCorrectAnswer(int number);
}
