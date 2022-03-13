package ru.otus;

import ru.otus.model.Answer;
import ru.otus.model.Question;
import ru.otus.model.Student;

import java.util.ArrayList;
import java.util.List;

public class TestData {

    public static List<Question> createQuestionsList(){
        List<Question> questionList = new ArrayList<>();

        List<String> answerText = new ArrayList<>();
        answerText.add("answer1");
        answerText.add("answer2");
        var answer = new Answer(answerText, 1);

        Question question1 = new Question(1, "question", answer);
        Question question2 = new Question(2, "question", answer);

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
