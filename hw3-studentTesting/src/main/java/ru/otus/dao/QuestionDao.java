package ru.otus.dao;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import ru.otus.configuration.ApplicationConfiguration;
import ru.otus.exception.DetailRuntimeException;
import ru.otus.model.Answer;
import ru.otus.model.Question;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionDao {

    private final ResourceLoader resourceLoader;
    private final ApplicationConfiguration applicationConfiguration;

    public QuestionDao(ResourceLoader resourceLoader, ApplicationConfiguration applicationConfiguration) {
        this.resourceLoader = resourceLoader;
        this.applicationConfiguration = applicationConfiguration;
    }

    private final static String COLUMN_SEPARATOR = ";";
    private final static String ANSWER_SEPARATOR = ",";

    public List<Question> getQuestionList(){
        var relativePath = applicationConfiguration.getCsvFileName();

        List<Question> questionsList = new ArrayList<>();
        int questionNumber = 0;

        try(BufferedReader bufferedReader = new BufferedReader
                (new InputStreamReader(resourceLoader.getResource(relativePath).getInputStream()))) {
            while(bufferedReader.ready()){
                var currentQuestion = new Question();
                var row = bufferedReader.readLine();
                var columnValues = row.split(COLUMN_SEPARATOR);

                currentQuestion.setId(++questionNumber);
                currentQuestion.setQuestionText(columnValues[0]);
                var answer = new Answer(Arrays.stream(columnValues[1].split(ANSWER_SEPARATOR)).collect(Collectors.toList()),
                        Integer.parseInt(columnValues[2]));
                currentQuestion.setAnswer(answer);

                questionsList.add(currentQuestion);
            }
        } catch (IOException ioe) {
            throw new DetailRuntimeException(MessageFormat.format("Failed to upload CSV-file: {0}", ioe));
        }

        return questionsList;
    }
}
