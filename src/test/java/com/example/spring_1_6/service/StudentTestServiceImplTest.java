package com.example.spring_1_6.service;




import com.example.spring_1_6.domain.Answer;
import com.example.spring_1_6.domain.Question;
import com.example.spring_1_6.service.impl.StudentTestServiceImpl;
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
public class StudentTestServiceImplTest {

    @Autowired
    private StudentTestServiceImpl service;
    @MockBean
    private MessageSource messageSource;
    @MockBean
    private IOService ioService;


    @BeforeEach
    void setUp(){
        service.setCorrectAnswersForPass(1);
    }

    @Test
    public void testWithCorrectAnswers(){
        List<Question> testQuestions = new ArrayList<>();
        String studentName = "Ivan Ivanov";
        String answers = "Saint-Petersburg, Moscow";

        testQuestions.add(new Question("The 2 most populated cities in Russia", List.of(new Answer("Moscow"), new Answer("Saint-Petersburg"))));

        when(messageSource.getMessage("test.passed.message", null, Locale.getDefault()))
                .thenReturn("Test passed");
        when(ioService.readString())
                .thenReturn(studentName)
                .thenReturn(answers);

        String result = service.test(testQuestions);

        Assertions.assertEquals("Test passed", result);
    }

    @Test
    public void testWithIncorrectAnswers(){
        List<Question> testQuestions = new ArrayList<>();
        String studentName = "Ivan Ivanov";
        String answers = "Saint-Petersburg, Kazan";

        testQuestions.add(new Question("The 2 most populated cities in Russia", List.of(new Answer("Moscow"), new Answer("Saint-Petersburg"))));

        when(messageSource.getMessage("test.failed.message", null, Locale.getDefault()))
                .thenReturn("Test failed");
        when(ioService.readString())
                .thenReturn(studentName)
                .thenReturn(answers);


        String result = service.test(testQuestions);

        Assertions.assertEquals("Test failed", result);
    }
}
