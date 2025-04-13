package com.example.spring_1_6.service;

import com.example.spring_1_6.dao.QuestionBookDao;
import com.example.spring_1_6.domain.Answer;
import com.example.spring_1_6.domain.Question;
import com.example.spring_1_6.service.impl.QuestionBookServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest(properties = "spring.profiles.active=test")
class QuestionBookServiceImplTest {

    @MockBean
    private QuestionBookDao questionBookDao;

    @Autowired
    private QuestionBookServiceImpl questionBookService;

    @Test
    void getAllQuestionsTest() {
        Question question1 = new Question("Question 1", List.of(new Answer("Answer 1")));
        Question question2 = new Question("Question 2", List.of(new Answer("Answer 2")));
        List<Question> expectedQuestions = Arrays.asList(question1, question2);


        when(questionBookDao.getAllQuestions()).thenReturn(expectedQuestions);

        List<Question> actualQuestions = questionBookService.getAllQuestions();

        assertEquals(expectedQuestions.size(), actualQuestions.size());
        assertEquals(expectedQuestions.get(0).getTextQuestion(), actualQuestions.get(0).getTextQuestion());
        assertEquals(expectedQuestions.get(1).getTextQuestion(), actualQuestions.get(1).getTextQuestion());
    }
}