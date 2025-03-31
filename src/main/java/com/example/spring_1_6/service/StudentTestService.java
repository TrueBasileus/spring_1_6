package com.example.spring_1_6.service;

import com.example.spring_1_6.domain.Answer;
import com.example.spring_1_6.domain.Question;
import com.example.spring_1_6.domain.QuestionBook;
import lombok.Setter;
import org.jline.reader.LineReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@PropertySource("classpath:application.yml")
@Service
@Setter
public class StudentTestService {

    int correctAnswersForPass;
    private MessageSource messageSource;
    private LineReader lineReader;

    public StudentTestService() {

    }

    @Autowired
    public StudentTestService(@Value("${answers.for.pass}") int correctAnswersForPass, MessageSource messageSource,@Lazy LineReader lineReader) {
        this.messageSource = messageSource;
        this.correctAnswersForPass = correctAnswersForPass;
        this.lineReader = lineReader;
    }

    public String test(QuestionBook questions) {
        System.out.println(messageSource.getMessage("initial.message", null, Locale.getDefault()));

        int correctAnswers = 0;
        for (Question question : questions.getQuestions()) {
            System.out.print(question.getTextQuestion() + " ");
            List<String> answers = question.getAnswers().stream().map(Answer::toString).collect(Collectors.toList());
            String[] studentsAnswers = lineReader.readLine().split(",[\\s]*");
            for (String studentsAnswer : studentsAnswers) {
                if (answers.stream().anyMatch(ans -> ans.equals(studentsAnswer))) {
                    answers.remove(studentsAnswer);
                }
            }
            if (answers.isEmpty()) {
                correctAnswers++;
            }
        }
            String str = correctAnswers < correctAnswersForPass ? messageSource.getMessage("test.failed.message", null, Locale.getDefault()) : messageSource.getMessage("test.passed.message", null, Locale.getDefault());
            return str;
        }
    }

