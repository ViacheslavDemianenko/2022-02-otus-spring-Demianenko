package ru.otus.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.model.Student;
import ru.otus.service.IOService;
import ru.otus.service.MessageSourceService;
import ru.otus.service.StudentService;


@Component
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final IOService ioService;
    private final MessageSourceService messageSourceService;

    public Student initializeStudent(){
        var student = new Student();

        var firstNameInput = ioService.readStringWithPrompt(messageSourceService.getMessage("student.firstname"));
        student.setFirstName(firstNameInput);

        var lastNameInput = ioService.readStringWithPrompt(messageSourceService.getMessage("student.lastname"));
        student.setLastName(lastNameInput);

        return student;
    }
}
