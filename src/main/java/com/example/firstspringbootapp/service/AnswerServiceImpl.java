package com.example.firstspringbootapp.service;

import com.example.firstspringbootapp.entity.Answer;
import com.example.firstspringbootapp.exception.AnswerNotFoundException;
import com.example.firstspringbootapp.repository.AnswerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerRepository answerRepository;

    @Override
    public Answer findBy(Integer id) {
        return answerRepository.findById(id)
                .orElseThrow(()->new AnswerNotFoundException("Answer with id "+id+" not found"));
    }

    @Override
    public List<Answer> findAll() {
        List<Answer> answers = answerRepository.findAll();
        if (answers.isEmpty())
            throw new AnswerNotFoundException("No answers found in database");
        return answers;
    }

    @Override
    public void save(Answer answer) {
        answerRepository.save(answer);
    }

    @Override
    public void update(Answer answer, Integer id) {
        Answer answerById = findBy(id);
        answerById.setCorrect(answer.getCorrect())
                .setName(answer.getName());
        answerRepository.save(answerById);
    }

    @Override
    public void delete(Integer id) {
        answerRepository.delete(findBy(id));
    }
}
