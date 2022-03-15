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
import ru.otus.model.TestResult;

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
    private StudentServiceImpl studentService;
    @Mock
    private IOServiceImpl ioService;
    @Mock
    private MessageSourceServiceImpl messageSourceService;

    private static final int NUMBER_OF_ANSWERS_FOR_TEST_PASS = 2;

    @BeforeEach
    void setUp(){
        testService = new TestServiceImpl(questionDao,
                studentService,
                ioService,
                messageSourceService,
                NUMBER_OF_ANSWERS_FOR_TEST_PASS);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3})
    void startTest(int inputAnswerNumber){
        //given
        var questionList = createQuestionsList();
        var student = createStudent();
        when(questionDao.getQuestionList()).thenReturn(questionList);
        when(studentService.initializeStudent()).thenReturn(student);
        when(ioService.readIntWithPrompt(anyString())).thenReturn(inputAnswerNumber);
        when(messageSourceService.getMessage(anyString())).thenReturn("message");

        //when
        var testResult = testService.runTest();

        //then
        assertNotNull(testResult);
        assertEquals(student.getFirstName(), testResult.getStudent().getFirstName());
        assertEquals(student.getLastName(), testResult.getStudent().getLastName());

        verify(messageSourceService,times(9)).getMessage(anyString());
    }
}