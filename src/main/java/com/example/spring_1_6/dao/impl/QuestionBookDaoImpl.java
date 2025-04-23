package com.example.spring_1_6.dao.impl;

import com.example.spring_1_6.dao.QuestionBookDao;
import com.example.spring_1_6.domain.Answer;
import com.example.spring_1_6.domain.Question;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.stream.Collectors;

@Repository
public class QuestionBookDaoImpl implements QuestionBookDao {
    private List<Question> questions;
    private final Locale locale;
    private final String fileName;

    public QuestionBookDaoImpl(Locale locale,@Value("${app.questions-basename}") String fileName) {
        this.fileName = fileName + "_" + locale + ".csv";
        this.locale = locale;

    }

    private List<Question> loadQuestions() {
        List<Question> result = new ArrayList<>();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream(fileName);
             BufferedReader reader = new BufferedReader(new InputStreamReader(input))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String question = parts[0];
                List<Answer> answers = Arrays.stream(parts)
                        .filter(str -> !str.contains("?"))
                        .map(Answer::new)
                        .collect(Collectors.toList());
                Question questionObj = new Question(question, answers);
                result.add(questionObj);
            }
        } catch(IOException e) {
            System.out.println("Ошибка при чтении файла");
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