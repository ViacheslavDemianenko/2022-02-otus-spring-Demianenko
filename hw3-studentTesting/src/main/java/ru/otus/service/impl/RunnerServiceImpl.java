package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.service.IOService;
import ru.otus.service.MessageSourceService;
import ru.otus.service.TestService;
import ru.otus.service.RunnerService;

@Service
@RequiredArgsConstructor
public class RunnerServiceImpl implements RunnerService {

    private final TestService testService;
    private final IOService ioService;
    private final MessageSourceService messageSourceService;

    @Override
    public void runTest(){
        var test = testService.startTest();
        ioService.outputString(messageSourceService.getMessage("test.student") + test.getStudent().getFirstName() + " " + test.getStudent().getLastName());
        ioService.outputString(messageSourceService.getMessage("test.result") + test.getResult());
        testService.checkNumberCorrectAnswer(test.getResult());
    }
}
