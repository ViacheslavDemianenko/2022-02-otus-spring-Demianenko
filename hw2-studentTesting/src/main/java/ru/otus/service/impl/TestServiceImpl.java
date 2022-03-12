package ru.otus.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.dao.QuestionDao;
import ru.otus.helper.StudentInitializer;
import ru.otus.model.Question;
import ru.otus.model.Test;
import ru.otus.service.IOService;
import ru.otus.service.TestService;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    private final QuestionDao questionDao;
    private final StudentInitializer studentInitializer;
    private final int numberOfAnswersForTestPass;
    private final IOService ioService;

    private final static String CONGRATS = "\nCONGRATS! You passed test";
    private final static String FAILED = "\nFAILED! You didn't pass test";

    public TestServiceImpl(QuestionDao questionDao,
                           StudentInitializer studentInitializer,
                           IOService ioService,
                           @Value("${number.test.pass}") int numberOfAnswersForTestPass) {
        this.questionDao = questionDao;
        this.studentInitializer = studentInitializer;
        this.ioService = ioService;
        this.numberOfAnswersForTestPass = numberOfAnswersForTestPass;
    }

    @Override
    public Test startTest(){

        List<Question> questionsList = questionDao.getQuestionList();
        List<Integer> studentAnswers = new ArrayList<>();

        var test = getTest();

        for(Question question : questionsList){
            ioService.outputString("Question №"+ question.getId() + ": " + question.getQuestionText());
            ioService.outputString("Answers: " + question.getAnswer().getAnswerText());
            studentAnswers.add(ioService.readIntWithPrompt("Enter number of correct answer:"));
        }
        test.setResult(checkAnswer(studentAnswers, questionsList));
        return test;
    }

    @Override
    public void checkNumberCorrectAnswer(int number){
        if(number >= numberOfAnswersForTestPass){
            ioService.outputString(CONGRATS);
        } else {
            ioService.outputString(FAILED);
        }
    }

    private Test getTest(){
        var test = new Test();
        test.setStudent(studentInitializer.initializeStudent());
        return test;
    }

    private int checkAnswer(List<Integer> studentAnswers, List<Question> questionsList){
        int result = 0;
        for (int i = 0; i < studentAnswers.size(); i++) {
            if (questionsList.get(i).getAnswer().getRightAnswerNumber() == studentAnswers.get(i)) {
                result++;
            }
        }
        return result;
    }
}
