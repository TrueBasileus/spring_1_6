package com.example.spring_1_6.dao;


import com.example.spring_1_6.dao.impl.QuestionBookDaoImpl;
import com.example.spring_1_6.domain.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@SpringBootTest(properties = "spring.profiles.active=test")
@TestPropertySource(properties = {
        "questions.amount=2"
        })
public class QuestionBookDaoImplTest {

    @MockBean
    private MessageSource messageSource;

    @Autowired
    private QuestionBookDaoImpl questionBookDaoImpl;


    @Test
    public void testLoadQuestions() {
        when(messageSource.getMessage(eq("survey.question1"), any(), any()))
                .thenReturn("What is your name?");
        when(messageSource.getMessage(eq("survey.answer1"), any(), any()))
                .thenReturn("Alice,Bob,Charlie");
        when(messageSource.getMessage(eq("survey.question2"), any(), any()))
                .thenReturn("What is your favorite color?");
        when(messageSource.getMessage(eq("survey.answer2"), any(), any()))
                .thenReturn("Red,Green,Blue");

        List<Question> questions = questionBookDaoImpl.getAllQuestions();

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