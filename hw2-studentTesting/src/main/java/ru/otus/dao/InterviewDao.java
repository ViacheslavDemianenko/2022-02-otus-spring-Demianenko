package ru.otus.dao;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;
import ru.otus.exception.DetailRuntimeException;
import ru.otus.model.Interview;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class InterviewDao {

    private final ResourceLoader resourceLoader;
    @Value("${csv.relative.path}")
    private String relativePath;

    private final static String COLUMN_SEPARATOR = ";";
    private final static String ANSWER_SEPARATOR = ",";

    public List<Interview> getInterviewList(){
        List<Interview> interviewList = new ArrayList<>();
        int questionNumber = 0;
        try(BufferedReader bufferedReader = new BufferedReader
                (new InputStreamReader(resourceLoader.getResource(relativePath).getInputStream()))) {
            while(bufferedReader.ready()){
                var currentInterview = new Interview();
                var row = bufferedReader.readLine();
                var columnValues = row.split(COLUMN_SEPARATOR);

                currentInterview.setId(++questionNumber);
                currentInterview.setQuestionText(columnValues[0]);
                currentInterview.setAnswers(Arrays.stream(columnValues[1].split(ANSWER_SEPARATOR)).collect(Collectors.toList()));
                currentInterview.setRightAnswerNumber(Integer.parseInt(columnValues[2]));

                interviewList.add(currentInterview);
            }
        } catch (IOException ioe) {
            throw new DetailRuntimeException(MessageFormat.format("Не удалось загрузить csv-файл: {0}", ioe));
        }

        return interviewList;
    }
}
