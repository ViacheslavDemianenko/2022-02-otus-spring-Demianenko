package ru.otus;

import ru.otus.model.Interview;
import ru.otus.model.Student;

import java.util.ArrayList;
import java.util.List;

public class TestData {

    public static List<Interview> createInterviewList(){
        List<Interview> interviewList = new ArrayList<>();

        List<String> answerList = new ArrayList<>();
        answerList.add("answer1");
        answerList.add("answer2");

        Interview interview1 = new Interview(1, "question", answerList, 1);
        Interview interview2 = new Interview(2, "question", answerList, 1);

        interviewList.add(interview1);
        interviewList.add(interview2);

        return interviewList;
    }

    public static Student createStudent(){
        var student = new Student();
        student.setFirstName("Viacheslav");
        student.setLastName("Demianenko");
        student.setResult(2);
        return student;
    }
}
