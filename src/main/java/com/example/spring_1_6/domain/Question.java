package com.example.spring_1_6.domain;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
@AllArgsConstructor
public class Question {
    private String textQuestion;
    private List<Answer> answers;


    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder(textQuestion).append(" ");
        for (int i = 0; i < answers.size(); i++) {
            builder.append(answers.get(i)).append(i == answers.size() - 1 ? "" : ", ");
        }
        return builder.toString();
    }
}
