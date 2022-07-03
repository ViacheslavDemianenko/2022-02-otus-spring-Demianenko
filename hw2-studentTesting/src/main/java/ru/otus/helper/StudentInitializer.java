package ru.otus.helper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.otus.model.Student;
import ru.otus.service.IOService;

import java.util.Scanner;

@Component
@RequiredArgsConstructor
public class StudentInitializer {

    private final IOService ioService;

    public Student initializeStudent(){
        var student = new Student();

        Scanner console = new Scanner(System.in);
        ioService.outputString("Enter your first name: ");
        String input = console.next();
        student.setFirstName(input);

        ioService.outputString("Enter your last name: ");
        input = console.next();
        student.setLastName(input);

        return student;
    }
}
