package com.example.firstspringbootapp.repository;

import com.example.firstspringbootapp.entity.Level;
import com.example.firstspringbootapp.entity.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Integer> {
    //Creates HQL queries
    List<Question> findQuestionByNameAndLevel(String name, Level level);
    Optional<Question> findQuestionByName(String name);
}
