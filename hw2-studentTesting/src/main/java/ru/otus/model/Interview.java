package ru.otus.model;

import lombok.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Interview {
    private int id;
    private String questionText;
    private List<String> answers;
    private int rightAnswerNumber;

    @Override
    public String toString() {
        return "Question №" + id + ": " + questionText + "\nAnswers: " + answers.toString() + "\nRight answer number: " + rightAnswerNumber + "\n";
    }
}
