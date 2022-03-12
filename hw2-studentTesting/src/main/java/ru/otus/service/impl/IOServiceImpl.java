package ru.otus.service.impl;

import org.springframework.stereotype.Service;
import ru.otus.service.IOService;

import java.util.InputMismatchException;
import java.util.Scanner;

@Service
public class IOServiceImpl implements IOService {

    private final Scanner input;

    public IOServiceImpl() {
        input = new Scanner(System.in);
    }

    @Override
    public void outputString(String s){
        System.out.println(s);
    }

    @Override
    public int readInt(){
        return Integer.parseInt(input.nextLine());
    }

    @Override
    public int readIntWithPrompt(String prompt){
        System.out.println(prompt);
        return Integer.parseInt(input.nextLine());
    }

    @Override
    public String readStringWithPrompt(String prompt){
        System.out.println(prompt);
        return input.nextLine();
    }

}
