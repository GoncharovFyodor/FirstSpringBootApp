package com.example.firstspringbootapp.service;

import com.example.firstspringbootapp.entity.Answer;
import com.example.firstspringbootapp.entity.Question;
import com.example.firstspringbootapp.exception.AnswerQuantityMismatchException;
import com.example.firstspringbootapp.exception.QuestionExistException;
import com.example.firstspringbootapp.exception.QuestionNotFoundException;
import com.example.firstspringbootapp.repository.QuestionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository questionRepository;
    @Override
    public Question findBy(Integer id) {
        return questionRepository.findById(id)
                .orElseThrow(()->new QuestionNotFoundException("Question with id "+id+" not found"));
    }

    @Override
    public List<Question> findAll() {
        List<Question> questions = questionRepository.findAll();
        if (questions.isEmpty())
            throw new QuestionNotFoundException("No profiles found in database");
        return questions;
    }

    @Override
    public void save(Question question) {
        checkCountCorrectAnswer(question);
        checkQuestionExistByName(question.getName());
        List<Answer> answers = question.getAnswers();
        answers.forEach(a->a.setQuestion(question));
        question.setAnswers(answers);
        questionRepository.save(question);
    }

    @Override
    public void update(Question question, Integer id) {
        checkCountCorrectAnswer(question);
        checkQuestionExistByName(question.getName());
        Question oldQuestion = findBy(id);
        oldQuestion.setName(question.getName())
                .setNumOfCorrect(question.getNumOfCorrect())
                .setProfile(question.getProfile())
                .setLevel(question.getLevel())
                .setAnswers(question.getAnswers());
        questionRepository.save(oldQuestion);
    }

    @Override
    public void delete(Integer id) {
        questionRepository.delete(findBy(id));
    }

    public void checkCountCorrectAnswer(Question question){
        long countAnswerCorrect = question.getAnswers()
                .stream().filter(Answer::getCorrect)
                .count();
        if (question.getNumOfCorrect() != countAnswerCorrect){
            throw new AnswerQuantityMismatchException("Quantity add true answers, is not correct");
        }
    }

    public void checkQuestionExistByName(String questionName){
        boolean isPresent = questionRepository.findQuestionByName(questionName).isPresent();
        if(isPresent){
            throw new QuestionExistException("Question "+questionName+" already exists");
        }
    }
}
