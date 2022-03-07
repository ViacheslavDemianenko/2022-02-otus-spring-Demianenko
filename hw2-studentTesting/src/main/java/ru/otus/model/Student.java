package ru.otus.model;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Student {
    private String firstName;
    private String lastName;
    private int result;

    @Override
    public String toString() {
        return "Студент: " + firstName + " " + lastName + "\nПравильных ответов: " + result;
    }
}
