package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.dao.InterviewDao;
import ru.otus.model.Interview;
import ru.otus.model.Student;
import ru.otus.service.QuestionService;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

@RequiredArgsConstructor
@Service
public class QuestionServiceImpl implements QuestionService {

    private final InterviewDao interviewDao;

    @Value("${number.test.pass}")
    private int numberOfAnswersForTestPass;

    @Override
    public Student startTest(){
        List<Interview> interviewList = interviewDao.getInterviewList();
        List<Integer> studentAnswers = new ArrayList<>();
        Scanner console = new Scanner(System.in);

        var student = initializeStudent();

        for(Interview interview : interviewList){
            System.out.println("Question №"+interview.getId() + ": " + interview.getQuestionText());
            System.out.println("Answers: " + interview.getAnswers().toString());
            System.out.println("Enter number of correct answer: ");

            try{
                studentAnswers.add(console.nextInt());
            } catch (InputMismatchException IME){
                System.out.println("You don't put a number. Try again");
                console.nextLine();
                studentAnswers.add(console.nextInt());
            }
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

    private Student initializeStudent(){
        var student = new Student();

        Scanner console = new Scanner(System.in);
        System.out.println("Enter your first name: ");
        String input = console.next();
        student.setFirstName(input);

        System.out.println("Enter your last name: ");
        input = console.next();
        student.setLastName(input);

        return student;
    }

    @Override
    public void checkNumberCorrectAnswer(int number){
        if(number < numberOfAnswersForTestPass){
            System.out.println("\nFAILED! You didn't pass test");
        } else {
            System.out.println("\nCONGRATS! You passed test");
        }
    }
}
