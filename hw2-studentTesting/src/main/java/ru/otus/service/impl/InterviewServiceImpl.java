package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.dao.InterviewDao;
import ru.otus.exception.DetailRuntimeException;
import ru.otus.helper.ConsoleAnswerHelper;
import ru.otus.helper.StudentInitializer;
import ru.otus.model.Interview;
import ru.otus.model.Student;
import ru.otus.service.InterviewService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
@Service
public class InterviewServiceImpl implements InterviewService {

    private final InterviewDao interviewDao;
    private final StudentInitializer studentInitializer;
    private final ConsoleAnswerHelper consoleAnswerHelper;

    @Value("${number.test.pass}")
    private int numberOfAnswersForTestPass;

    @Override
    public Student startTest(){
        List<Interview> interviewList = interviewDao.getInterviewList();
        List<Integer> studentAnswers = new ArrayList<>();
        Scanner console = new Scanner(System.in);

        var student = studentInitializer.initializeStudent();

        for(Interview interview : interviewList){
            System.out.println("Question №"+interview.getId() + ": " + interview.getQuestionText());
            System.out.println("Answers: " + interview.getAnswers().toString());
            System.out.println("Enter number of correct answer: ");

            consoleAnswerHelper.getAnswerFromStudent(console, studentAnswers);
        }
        student.setResult(checkAnswer(studentAnswers, interviewList));
        return student;
    }

    private int checkAnswer(List<Integer> studentAnswers, List<Interview> interviewList){
        int result = 0;
        for (int i = 0; i < studentAnswers.size(); i++) {
            if (interviewList.get(i).getRightAnswerNumber() == (studentAnswers.get(i))) {
                result++;
            }
        }
        return result;
    }

    @Override
    public void checkNumberCorrectAnswer(int number){
        if(number > numberOfAnswersForTestPass){
            System.out.println("\nCONGRATS! You passed test");
        } else {
            throw new DetailRuntimeException("FAILED! You didn't pass test");
        }
    }
}
