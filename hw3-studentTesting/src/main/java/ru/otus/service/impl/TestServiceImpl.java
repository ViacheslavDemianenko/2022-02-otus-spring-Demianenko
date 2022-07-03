package ru.otus.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.dao.QuestionDao;
import ru.otus.model.Answer;
import ru.otus.model.Question;
import ru.otus.model.TestResult;
import ru.otus.service.IOService;
import ru.otus.service.MessageSourceService;
import ru.otus.service.TestService;

import java.util.List;
import java.util.stream.Collectors;

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

        var testResult = getTest();
        var result = 0;

        for(Question question : questionsList){
            ioService.outputString(messageSourceService.getMessage("questions") +
                    question.getId() + ": " +
                    question.getQuestionText());

            ioService.outputString(messageSourceService.getMessage("answers") +
                    question.getAnswers().stream().map(Answer::getAnswerText).collect(Collectors.toList()));

            var selectedNumber = ioService.readIntWithPrompt("Enter number of correct answer:");
            if(question.getAnswers().get(selectedNumber - 1).isCorrect()){
                result++;
            }
        }

        testResult.setResult(result);
        printTestResult(testResult);
        return testResult;
    }

    @Override
    public void printTestResult(TestResult testResult){
        ioService.outputString(messageSourceService.getMessage("test.student") +
                testResult.getStudent().getFirstName() + " " + testResult.getStudent().getLastName());

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
}
