package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.service.IOService;
import ru.otus.service.TestService;
import ru.otus.service.RunnerService;

@Service
@RequiredArgsConstructor
public class RunnerServiceImpl implements RunnerService {

    private final TestService testService;
    private final IOService ioService;

    @Override
    public void runTest(){
        var test = testService.startTest();
        ioService.outputString("Правильных ответов: " + test.getResult());
        testService.checkNumberCorrectAnswer(test.getResult());
    }
}
