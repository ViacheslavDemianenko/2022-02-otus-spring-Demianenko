package ru.otus.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import ru.otus.dao.QuestionDao;
import ru.otus.helper.StudentInitializer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;
import static ru.otus.TestData.*;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class TestServiceImplTest {

    private TestServiceImpl testService;
    @Mock
    private QuestionDao questionDao;
    @Mock
    private StudentInitializer studentInitializer;
    @Mock
    private IOServiceImpl ioService;
    @Mock
    private MessageSourceServiceImpl messageSourceService;

    private static final int NUMBER_OF_ANSWERS_FOR_TEST_PASS = 2;

    @BeforeEach
    void setUp(){
        testService = new TestServiceImpl(questionDao, studentInitializer, ioService, messageSourceService,2);
    }

    @Test
    void startTest(){
        //given
        var questionList = createQuestionsList();
        var student = createStudent();
        when(questionDao.getQuestionList()).thenReturn(questionList);
        when(studentInitializer.initializeStudent()).thenReturn(student);
        when(ioService.readIntWithPrompt(anyString())).thenReturn(1);
        when(messageSourceService.getMessage(anyString())).thenReturn("message");

        //when
        var test = testService.startTest();

        //then
        assertNotNull(test);
        assertEquals(student.getFirstName(), test.getStudent().getFirstName());
        assertEquals(student.getLastName(), test.getStudent().getLastName());

    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3})
    void checkNumberCorrectAnswer(int number){
        testService.checkNumberCorrectAnswer(number);
        when(messageSourceService.getMessage(anyString())).thenReturn("message");
        if(number >= NUMBER_OF_ANSWERS_FOR_TEST_PASS){
            verify(ioService,times(1)).outputString(any());
        } else {
            verify(ioService,times(1)).outputString(any());
        }
    }
}