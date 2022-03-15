package ru.otus.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.dao.QuestionDao;
import ru.otus.model.Question;
import ru.otus.model.TestResult;
import ru.otus.service.IOService;
import ru.otus.service.MessageSourceService;
import ru.otus.service.TestService;

import java.util.ArrayList;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {

    private final QuestionDao questionDao;
    private final StudentServiceImpl studentInitializer;
    private final int numberOfAnswersForTestPass;
    private final IOService ioService;
    private final MessageSourceService messageSourceService;

    public TestServiceImpl(QuestionDao questionDao,
                           StudentServiceImpl studentInitializer,
                           IOService ioService,
                           MessageSourceService messageSourceService,
                           @Value("${application.number.test.pass}") int numberOfAnswersForTestPass) {
        this.questionDao = questionDao;
        this.studentInitializer = studentInitializer;
        this.ioService = ioService;
        this.messageSourceService = messageSourceService;
        this.numberOfAnswersForTestPass = numberOfAnswersForTestPass;
    }

    @Override
    public TestResult runTest(){

        List<Question> questionsList = questionDao.getQuestionList();
        List<Integer> studentAnswers = new ArrayList<>();

        var testResult = getTest();

        for(Question question : questionsList){
            ioService.outputString(messageSourceService.getMessage("questions") +
                    question.getId() + ": " +
                    question.getQuestionText());

            ioService.outputString(messageSourceService.getMessage("answers") +
                    question.getAnswer().getAnswerText());
            studentAnswers.add(ioService.readIntWithPrompt(messageSourceService.getMessage("test.getanswer")));
        }
        testResult.setResult(checkAnswer(studentAnswers, questionsList));

        printTestResult(testResult);

        return testResult;
    }

    @Override
    public void printTestResult(TestResult testResult){
        ioService.outputString(messageSourceService.getMessage("test.student") + testResult.getStudent().getFirstName() + " " + testResult.getStudent().getLastName());
        ioService.outputString(messageSourceService.getMessage("test.result") + testResult.getResult());
        checkNumberCorrectAnswer(testResult.getResult());
    }

    private void checkNumberCorrectAnswer(int number){
        if(number >= numberOfAnswersForTestPass){
            ioService.outputString(messageSourceService.getMessage("test.pass"));
        } else {
            ioService.outputString(messageSourceService.getMessage("test.fail"));
        }
    }

    private TestResult getTest(){
        var testResult = new TestResult();
        testResult.setStudent(studentInitializer.initializeStudent());
        return testResult;
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
