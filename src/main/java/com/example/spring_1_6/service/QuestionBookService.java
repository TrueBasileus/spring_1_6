package com.example.spring_1_6.service;

import com.example.spring_1_6.domain.Answer;
import com.example.spring_1_6.domain.Question;
import com.example.spring_1_6.domain.QuestionBook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@Service
public class QuestionBookService {
    MessageSource messageSource;

    @Autowired
    public QuestionBookService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public QuestionBook loadQuestions(){
        List<Question> result = new ArrayList<>();

        // Загружаем вопросы
        for (int i = 1; i <= 2; i++) {
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

        return new QuestionBook(result);
    }

}
