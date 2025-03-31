package com.example.spring_1_6;

import com.example.spring_1_6.domain.QuestionBook;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import com.example.spring_1_6.service.StudentTestService;

@SpringBootApplication
public class Spring16Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Spring16Application.class, args);
        StudentTestService studentTestService = context.getBean(StudentTestService.class);
        studentTestService.test(context.getBean(QuestionBook.class));
    }
}
