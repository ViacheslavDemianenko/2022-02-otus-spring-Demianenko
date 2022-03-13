package ru.otus;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import ru.otus.service.RunnerService;

@SpringBootApplication
@RequiredArgsConstructor
public class SpringBootApp implements CommandLineRunner {

    private final RunnerService runnerService;

    public static void main(String[] args) {
        var app = new SpringApplication(SpringBootApp.class);
        app.run();
    }

    @Override
    public void run(String... args) {
        runnerService.runTest();
    }
}