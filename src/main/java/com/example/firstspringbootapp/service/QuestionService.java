package com.example.firstspringbootapp.service;

import com.example.firstspringbootapp.entity.Question;

import java.util.List;

public interface QuestionService {
    Question findBy(Integer id);
    List<Question> findAll();
    void save(Question question);
    void update(Question question,Integer id);
    void delete(Integer id);
}
