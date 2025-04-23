package com.example.spring_1_6.shell.impl;

import com.example.spring_1_6.dao.QuestionBookDao;
import com.example.spring_1_6.domain.Question;
import com.example.spring_1_6.shell.QuestionBookShell;
import lombok.AllArgsConstructor;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

@ShellComponent
@AllArgsConstructor
public class QuestionBookShellImpl implements QuestionBookShell {
    private QuestionBookDao questionBookDao;

    @Override
    @ShellMethod(value = "Show questions", key = {"q", "questions"})
    public String showQuestions(){
        return questionBookDao.getAllQuestions().stream().map(Question::toString).reduce((q1, q2)-> q1 + "\n" + q2).orElse("There are no questions");
    }

}
