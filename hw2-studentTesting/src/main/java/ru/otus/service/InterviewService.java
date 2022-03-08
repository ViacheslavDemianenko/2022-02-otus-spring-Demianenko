package ru.otus.service;

import ru.otus.model.Student;


public interface InterviewService {

    Student startTest();

    void checkNumberCorrectAnswer(int number);
}
