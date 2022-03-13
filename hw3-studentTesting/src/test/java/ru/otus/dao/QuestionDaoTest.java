package ru.otus.dao;

import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import ru.otus.configuration.ApplicationConfiguration;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
class QuestionDaoTest {

    private QuestionDao questionDao;
    private ApplicationConfiguration applicationConfiguration;

    @Mock
    private ResourceLoader resourceLoader;
    @Mock
    private Resource resource;


    private final static String CSV_RELATIVE_PATH = "classpath:questions_ru.csv";
    @BeforeEach
    void setUp(){
        questionDao = new QuestionDao(resourceLoader, applicationConfiguration);
    }

    @Test
    @SneakyThrows
    void getInterviewList(){
        //given
        ClassLoader classLoader = getClass().getClassLoader();
        var csvInputStream = classLoader.getResourceAsStream(CSV_RELATIVE_PATH);
        //var csvInputStream = classLoader.getResourceAsStream(applicationConfiguration.getCsvFileName());

        when(resourceLoader.getResource(anyString())).thenReturn(resource);
        when(resource.getInputStream()).thenReturn(csvInputStream);

        //when
        var interviewList = questionDao.getQuestionList();

        //then
        assertNotNull(interviewList);
        assertFalse(interviewList.isEmpty());
    }
}
