package ru.otus;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import ru.otus.service.PrintService;
import ru.otus.service.QuestionService;

@Configuration
@PropertySource("classpath:application.properties")
@ComponentScan
public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new AnnotationConfigApplicationContext(Main.class);

        PrintService printService = context.getBean("printServiceImpl", PrintService.class);
        QuestionService questionService = context.getBean("questionServiceImpl", QuestionService.class);

        var student = questionService.startTest();
        System.out.println(student.toString());

        questionService.checkNumberCorrectAnswer(student.getResult());
    }
}
