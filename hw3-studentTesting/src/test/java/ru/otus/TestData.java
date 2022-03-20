package ru.otus;

import ru.otus.model.Answer;
import ru.otus.model.Question;
import ru.otus.model.Student;

import java.util.ArrayList;
import java.util.List;

public class TestData {

    public static List<Question> createQuestionsList(){
        List<Question> questionList = new ArrayList<>();

        List<Answer> answersList = new ArrayList<>();
        answersList.add(new Answer("answer1", false));
        answersList.add(new Answer("answer2", false));
        answersList.add(new Answer("answer3", true));

        Question question1 = new Question(1, "question", answersList);
        Question question2 = new Question(2, "question", answersList);

        questionList.add(question1);
        questionList.add(question2);

        return questionList;
    }

    public static Student createStudent(){
        var student = new Student();
        student.setFirstName("Viacheslav");
        student.setLastName("Demianenko");
        return student;
    }
}
