package com.example.spring_1_6.dao;


import com.example.spring_1_6.dao.impl.QuestionBookDaoImpl;
import com.example.spring_1_6.domain.Question;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@TestPropertySource(properties = {
        "questions.amount=2", "spring.profiles.active=test"
})
public class QuestionBookDaoImplTest {
    @Autowired
    private QuestionBookDaoImpl questionBookDaoImpl;

    @Test
    public void testLoadQuestions() {

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