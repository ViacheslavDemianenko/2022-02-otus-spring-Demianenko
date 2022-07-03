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
import org.springframework.test.context.TestPropertySource;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
@TestPropertySource("classpath:application.properties")
class QuestionDaoTest {

    private QuestionDao questionDao;
    @Mock
    private ResourceLoader resourceLoader;
    @Mock
    private Resource resource;

    private static final String CSV_RELATIVE_PATH = "/Questions.csv";
    @BeforeEach
    void setUp(){
        questionDao = new QuestionDao(resourceLoader, CSV_RELATIVE_PATH);
    }

    @Test
    @SneakyThrows
    void getInterviewList(){
        //given
        ClassLoader classLoader = getClass().getClassLoader();
        var csvInputStream = classLoader.getResourceAsStream(CSV_RELATIVE_PATH);
        when(resourceLoader.getResource(anyString())).thenReturn(resource);
        when(resource.getInputStream()).thenReturn(csvInputStream);

        //when
        var interviewList = questionDao.getQuestionList();

        //then
        assertNotNull(interviewList);
        assertFalse(interviewList.isEmpty());
    }
}
