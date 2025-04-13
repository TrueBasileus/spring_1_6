package com.example.spring_1_6.dao.impl;

import com.example.spring_1_6.dao.QuestionBookDao;
import com.example.spring_1_6.domain.Answer;
import com.example.spring_1_6.domain.Question;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Repository
public class QuestionBookDaoImpl implements QuestionBookDao {
    private List<Question> questions;
    private final MessageSource messageSource;
    private final int amountOfQuestions;

    public QuestionBookDaoImpl(MessageSource messageSource, @Value("${questions.amount}") int amountOfQuestions) {
        this.messageSource = messageSource;
        this.amountOfQuestions = amountOfQuestions;
    }

    public List<Question> loadQuestions() {
        List<Question> result = new ArrayList<>();

        for (int i = 1; i <= amountOfQuestions; i++) {
            String questionKey = "survey.question" + i;
            String questionText = messageSource.getMessage(questionKey, null, Locale.getDefault());

            String answerKey = "survey.answer" + i;
            String answerText = messageSource.getMessage(answerKey, null, Locale.getDefault());

            String[] answerStrings = answerText != null ? answerText.split(",") : new String[0];
            List<Answer> answers = new ArrayList<>();

            for (String answerStr : answerStrings) {
                answers.add(new Answer(answerStr.trim()));
            }

            Question questionObj = new Question(questionText, answers);
            result.add(questionObj);
        }

        return result;
    }

    @Override
    public List<Question> getAllQuestions() {
        if(Objects.isNull(questions)) {
            questions = loadQuestions();
        }
        return questions;
    }
}