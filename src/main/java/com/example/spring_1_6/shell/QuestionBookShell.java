package com.example.spring_1_6.shell;

import com.example.spring_1_6.service.QuestionBookService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@AllArgsConstructor
public class QuestionBookShell {
    private QuestionBookService questionBookService;

    @ShellMethod(value = "Show questions", key = {"q", "questions"})
    public String showQuestions(){
        return questionBookService.loadQuestions().toString();
    }

}
