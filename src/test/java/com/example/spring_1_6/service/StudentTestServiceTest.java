package com.example.spring_1_6.service;




import com.example.spring_1_6.domain.Answer;
import com.example.spring_1_6.domain.Question;
import com.example.spring_1_6.domain.QuestionBook;
import org.jline.reader.LineReader;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.MessageSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static org.mockito.Mockito.when;

@SpringBootTest(properties = "spring.profiles.active=test")
public class StudentTestServiceTest {

    @Autowired
    private StudentTestService service;
    @MockBean
    private MessageSource messageSource;

    @MockBean
    private QuestionBook questionBook;

    @MockBean
    private LineReader lineReader;

    @BeforeEach
    void setUp(){
        service.setCorrectAnswersForPass(1);
    }

    @Test
    public void testWithCorrectAnswers(){
        List<Question> testQuestions = new ArrayList<>();
        testQuestions.add(new Question("The 2 most populated cities in Russia", List.of(new Answer("Moscow"), new Answer("Saint-Petersburg"))));

        when(questionBook.getQuestions()).thenReturn(testQuestions);
        when(messageSource.getMessage("test.passed.message", null, Locale.getDefault()))
                .thenReturn("Test passed");
        when(lineReader.readLine()).thenReturn("Moscow, Saint-Petersburg");
        String result = service.test(questionBook);

        Assertions.assertEquals("Test passed", result);
    }

    @Test
    public void testWithIncorrectAnswers(){
        List<Question> testQuestions = new ArrayList<>();
        testQuestions.add(new Question("The 2 most populated cities in Russia", List.of(new Answer("Moscow"), new Answer("Saint-Petersburg"))));

        when(questionBook.getQuestions()).thenReturn(testQuestions);
        when(messageSource.getMessage("test.failed.message", null, Locale.getDefault()))
                .thenReturn("Test failed");
        when(lineReader.readLine()).thenReturn("Kazan, Saint-Petersburg");

        String result = service.test(questionBook);

        Assertions.assertEquals("Test failed", result);
    }
}
