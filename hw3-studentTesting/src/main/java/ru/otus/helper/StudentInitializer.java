package ru.otus.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.model.Student;
import ru.otus.service.IOService;
import ru.otus.service.MessageSourceService;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class StudentInitializer {

    private final IOService ioService;
    private final MessageSourceService messageSourceService;

    public Student initializeStudent(){
        var student = new Student();

        Scanner console = new Scanner(System.in);
        ioService.outputString(messageSourceService.getMessage("student.firstname"));
        String input = console.next();
        student.setFirstName(input);

        ioService.outputString(messageSourceService.getMessage("student.lastname"));
        input = console.next();
        student.setLastName(input);

        return student;
    }
}
