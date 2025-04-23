package com.example.spring_1_6.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Locale;

@Configuration
public class AppConfig {
    @Bean
    public Locale defaultLocale(@Value("${app.default-locale}") String locale){
        return Locale.forLanguageTag(locale);
    }
}
