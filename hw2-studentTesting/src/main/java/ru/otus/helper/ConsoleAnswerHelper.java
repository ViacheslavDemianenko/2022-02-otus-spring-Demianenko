package ru.otus.helper;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class ConsoleAnswerHelper {

    public void getAnswerFromStudent(Scanner console, List<Integer> studentAnswers){
        try{
            studentAnswers.add(console.nextInt());
        } catch (InputMismatchException IME){
            System.out.println("You don't put a number. Try again");
            console.nextLine();
            studentAnswers.add(console.nextInt());
        }
    }
}
