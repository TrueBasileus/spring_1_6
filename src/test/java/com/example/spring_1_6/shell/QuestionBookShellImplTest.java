package com.example.spring_1_6.shell;

import com.example.spring_1_6.domain.Answer;
import com.example.spring_1_6.domain.Question;
import com.example.spring_1_6.service.QuestionBookService;
import com.example.spring_1_6.shell.impl.QuestionBookShellImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;


@SpringBootTest(properties = "spring.profiles.active=test")
class QuestionBookShellImplTest {
    @MockBean
    private QuestionBookService questionBookService;

    @Autowired
    private QuestionBookShellImpl shell;
    @Test
    void showQuestionsTest(){

        List<Question> mockQuestions = List.of(
                new Question("What is Spring?", List.of(new Answer("Framework"), new Answer("Library"))),
                new Question("What is JPA?", List.of(new Answer("API"))));
        when(questionBookService.getAllQuestions()).thenReturn(mockQuestions);

        String result = shell.showQuestions();

        assertThat(result).isEqualTo(""" 
                What is Spring? Framework, Library
                What is JPA? API""");
    }

}