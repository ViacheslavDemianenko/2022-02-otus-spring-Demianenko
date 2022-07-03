package ru.otus.service;

import ru.otus.model.TestResult;

public interface TestService {

    TestResult runTest();

    void printTestResult(TestResult testResult);
}
