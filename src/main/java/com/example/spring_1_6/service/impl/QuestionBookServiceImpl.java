package com.example.spring_1_6.service.impl;

import com.example.spring_1_6.dao.QuestionBookDao;
import com.example.spring_1_6.domain.Question;
import com.example.spring_1_6.service.QuestionBookService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class QuestionBookServiceImpl implements QuestionBookService {
    private final QuestionBookDao questionBookDao;

    @Override
    public List<Question> getAllQuestions() {
        return questionBookDao.getAllQuestions();
    }

    @Override
    public void printAllQuestions(){
        questionBookDao.getAllQuestions().forEach(System.out::println);
    }
}