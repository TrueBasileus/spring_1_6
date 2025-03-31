package com.example.spring_1_6.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Answer {
    private String textAnswer;

    @Override
    public String toString() {
        return textAnswer;
    }
}
