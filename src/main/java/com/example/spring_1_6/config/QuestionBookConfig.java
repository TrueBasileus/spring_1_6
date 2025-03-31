package com.example.spring_1_6.config;

import com.example.spring_1_6.domain.QuestionBook;
import com.example.spring_1_6.service.QuestionBookService;
import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:application.yml")
@AllArgsConstructor
public class QuestionBookConfig {
    private QuestionBookService service;

//    @Bean
//    public QuestionBookService createQuestionBookService(MessageSource messageSource) {
//        return new QuestionBookService(messageSource);
//    }

    @Bean
    public QuestionBook createQuestionBook() {
        return service.loadQuestions();
    }
}
