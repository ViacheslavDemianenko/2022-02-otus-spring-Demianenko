package ru.otus.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;
import org.springframework.test.util.ReflectionTestUtils;
import ru.otus.dao.InterviewDao;
import ru.otus.exception.DetailRuntimeException;
import ru.otus.helper.ConsoleAnswerHelper;
import ru.otus.helper.StudentInitializer;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static ru.otus.TestData.createInterviewList;
import static ru.otus.TestData.createStudent;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class InterviewServiceImplTest {
    @InjectMocks
    private InterviewServiceImpl interviewService;
    @Mock
    private InterviewDao interviewDao;
    @Mock
    private StudentInitializer studentInitializer;
    @Mock
    private ConsoleAnswerHelper consoleAnswerHelper;

    private static final int NUMBER_OF_ANSWERS_FOR_TEST_PASS = 2;

    @BeforeEach
    void setUp(){
        ReflectionTestUtils.setField(interviewService,
                "numberOfAnswersForTestPass",
                NUMBER_OF_ANSWERS_FOR_TEST_PASS);
    }

    @Test
    void startTest(){
        //given
        when(interviewDao.getInterviewList()).thenReturn(createInterviewList());
        when(studentInitializer.initializeStudent()).thenReturn(createStudent());
        doNothing().when(consoleAnswerHelper).getAnswerFromStudent(any(Scanner.class), anyList());

        //when
        var student = interviewService.startTest();

        //then
        assertNotNull(student);
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 3})
    void checkNumberCorrectAnswer(int number){
        if(number > NUMBER_OF_ANSWERS_FOR_TEST_PASS){
            assertDoesNotThrow(() -> interviewService.checkNumberCorrectAnswer(number));
        } else {
            var exception = assertThrows(DetailRuntimeException.class,
                    () -> interviewService.checkNumberCorrectAnswer(number));
            assertNotNull(exception);
        }
    }
}