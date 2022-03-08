package ru.otus.helper;

import ru.otus.model.Student;

import java.util.Scanner;

public class StudentInitializer {

    public Student initializeStudent(){
        var student = new Student();

        Scanner console = new Scanner(System.in);
        System.out.println("Enter your first name: ");
        String input = console.next();
        student.setFirstName(input);

        System.out.println("Enter your last name: ");
        input = console.next();
        student.setLastName(input);

        return student;
    }
}
