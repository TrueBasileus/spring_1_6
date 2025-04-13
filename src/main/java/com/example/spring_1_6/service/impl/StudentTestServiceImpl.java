package com.example.spring_1_6.service.impl;

import com.example.spring_1_6.domain.Answer;
import com.example.spring_1_6.domain.Question;
import com.example.spring_1_6.service.IOService;
import com.example.spring_1_6.service.StudentTestService;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@PropertySource("classpath:application.yml")
@Service
@Setter
public class StudentTestServiceImpl implements StudentTestService {

    private int correctAnswersForPass;
    private MessageSource messageSource;
    private IOService ioService;

    public StudentTestServiceImpl() {

    }

    @Autowired
    public StudentTestServiceImpl(@Value("${answers.for.pass}") int correctAnswersForPass, MessageSource messageSource, IOService ioService) {
        this.messageSource = messageSource;
        this.correctAnswersForPass = correctAnswersForPass;
        this.ioService = ioService;
    }

    @Override
    public String test(List<Question> questions) {
        ioService.println(messageSource.getMessage("student.name.request", null, Locale.getDefault()));
        String studentName = ioService.readString();

        ioService.println(messageSource.getMessage("initial.message", new Object[]{studentName}, Locale.getDefault()));
        int correctAnswers = 0;
        for (Question question : questions) {
            System.out.print(question.getTextQuestion() + " ");
            List<String> answers = question.getAnswers().stream().map(Answer::toString).collect(Collectors.toList());
            String[] studentsAnswers = ioService.readString().split(",[\\s]*");
            for (String studentsAnswer : studentsAnswers) {
                if (answers.stream().anyMatch(ans -> ans.equals(studentsAnswer))) {
                    answers.remove(studentsAnswer);
                }
            }
            if (answers.isEmpty()) {
                correctAnswers++;
            }
        }
        return correctAnswers < correctAnswersForPass
                ? messageSource.getMessage("test.failed.message", null, Locale.getDefault())
                : messageSource.getMessage("test.passed.message", null, Locale.getDefault());

    }
}
