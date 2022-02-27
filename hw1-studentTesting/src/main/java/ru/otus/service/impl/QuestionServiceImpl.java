package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.ResourceLoader;
import ru.otus.model.Question;
import ru.otus.service.QuestionService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    private final ResourceLoader resourceLoader;
    private final String relativePath;

    @Override
    public void printQuestions(){
        List<Question> questions = getQuestions();
        if(!questions.isEmpty()){
            questions.forEach(q -> System.out.println(q.toString()));
        }
    }

    @Override
    public List<Question> getQuestions(){
        List<Question> questionList = new ArrayList<>();
        int questionNumber = 0;
        try(BufferedReader bufferedReader = new BufferedReader
                (new InputStreamReader(resourceLoader.getResource(relativePath).getInputStream()))) {
            while(bufferedReader.ready()){
                questionList.add(new Question(++questionNumber,bufferedReader.readLine()));
            }
        } catch (IOException ioe) {
            throw new RuntimeException(MessageFormat.format("Не удалось загрузить csv-файл: {0}", ioe));
        }

        return questionList;
    }
}
