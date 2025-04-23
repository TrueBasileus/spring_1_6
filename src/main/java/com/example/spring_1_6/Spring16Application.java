package com.example.spring_1_6;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;

@SpringBootApplication
@Slf4j
public class Spring16Application {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(Spring16Application.class, args);
        System.out.println(context.getBean(MessageSource.class).getClass().getSimpleName());
    }
}
