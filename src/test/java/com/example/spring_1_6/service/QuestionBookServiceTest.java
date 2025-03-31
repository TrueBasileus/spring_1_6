package com.example.spring_1_6.service;


import com.example.spring_1_6.domain.Question;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;

import java.util.List;
import java.util.Locale;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(properties = "spring.profiles.active=test")
public class QuestionBookServiceTest {


    @Autowired
    private QuestionBookService questionBookService;

    @MockBean
    private MessageSource messageSource;


    @Test
    public void testLoadQuestions() {
        // Настройка Mock-объектов
        when(messageSource.getMessage("survey.question1", null, Locale.getDefault()))
                .thenReturn("What is your name?");
        when(messageSource.getMessage("survey.answer1", null, Locale.getDefault()))
                .thenReturn("Alice,Bob,Charlie");

        when(messageSource.getMessage("survey.question2", null, Locale.getDefault()))
                .thenReturn("What is your favorite color?");
        when(messageSource.getMessage("survey.answer2", null, Locale.getDefault()))
                .thenReturn("Red,Green,Blue");

        List<Question> questions = questionBookService.loadQuestions().getQuestions();

        assertEquals(2, questions.size());

        Question question1 = questions.get(0);
        assertEquals("What is your name?", question1.getTextQuestion());
        assertEquals(3, question1.getAnswers().size());
        assertEquals("Alice", question1.getAnswers().get(0).getTextAnswer());
        assertEquals("Bob", question1.getAnswers().get(1).getTextAnswer());
        assertEquals("Charlie", question1.getAnswers().get(2).getTextAnswer());

        Question question2 = questions.get(1);
        assertEquals("What is your favorite color?", question2.getTextQuestion());
        assertEquals(3, question2.getAnswers().size());
        assertEquals("Red", question2.getAnswers().get(0).getTextAnswer());
        assertEquals("Green", question2.getAnswers().get(1).getTextAnswer());
        assertEquals("Blue", question2.getAnswers().get(2).getTextAnswer());
    }
}