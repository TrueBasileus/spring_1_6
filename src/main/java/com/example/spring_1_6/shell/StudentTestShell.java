package com.example.spring_1_6.shell;

import com.example.spring_1_6.service.QuestionBookService;
import com.example.spring_1_6.service.StudentTestService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@AllArgsConstructor
public class StudentTestShell {
    private StudentTestService studentTestService;
    private QuestionBookService questionBookService;
    @ShellMethod(value = "Begin test", key = {"t", "test"})
    public String beginTest(){
        return studentTestService.test(questionBookService.loadQuestions());
    }
}
