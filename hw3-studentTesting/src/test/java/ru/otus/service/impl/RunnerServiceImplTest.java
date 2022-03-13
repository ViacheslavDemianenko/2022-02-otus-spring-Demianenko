package ru.otus.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.junit.jupiter.MockitoSettings;
import org.mockito.quality.Strictness;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;
import static ru.otus.TestData.createStudent;

@ExtendWith(MockitoExtension.class)
@MockitoSettings(strictness = Strictness.LENIENT)
public class RunnerServiceImplTest {

    private RunnerServiceImpl runnerService;
    @Mock
    private IOServiceImpl ioService;
    @Mock
    private TestServiceImpl testService;
    @Mock
    private MessageSourceServiceImpl messageSourceService;

    @BeforeEach
    void setUp(){
        runnerService = new RunnerServiceImpl(testService, ioService, messageSourceService);
    }

    @Test
    void runTest(){
        //given
        var test = new ru.otus.model.Test();
        test.setResult(5);
        test.setStudent(createStudent());

        when(testService.startTest()).thenReturn(test);
        doNothing().when(testService).checkNumberCorrectAnswer(anyInt());

        //when
        runnerService.runTest();

        //then
        verify(ioService, times(2)).outputString(anyString());
    }
}
