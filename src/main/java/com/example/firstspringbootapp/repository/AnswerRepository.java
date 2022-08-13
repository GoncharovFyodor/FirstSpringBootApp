package com.example.firstspringbootapp.repository;

import com.example.firstspringbootapp.entity.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerRepository extends JpaRepository<Answer,Integer> {
    /*@Override
    <S extends Answer> S save(S entity);

    @Override
    Optional<Answer> findById(Integer integer);*/
}
