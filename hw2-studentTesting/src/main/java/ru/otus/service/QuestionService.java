package ru.otus.service;

import ru.otus.model.Student;


public interface QuestionService {

    Student startTest();

    void checkNumberCorrectAnswer(int number);
}
