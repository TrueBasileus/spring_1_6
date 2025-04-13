package com.example.spring_1_6.dao;

import com.example.spring_1_6.domain.Question;

import java.util.List;

public interface QuestionBookDao {

    List<Question> getAllQuestions();
}
