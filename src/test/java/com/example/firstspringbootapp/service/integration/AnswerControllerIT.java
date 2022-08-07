package com.example.firstspringbootapp.service.integration;

import com.example.firstspringbootapp.controller.AnswerController;
import com.example.firstspringbootapp.entity.Answer;
import com.example.firstspringbootapp.entity.Question;
import com.example.firstspringbootapp.repository.AnswerRepository;
import com.example.firstspringbootapp.repository.QuestionRepository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.*;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class AnswerControllerIT {
    @LocalServerPort
    private int port;
    private final String HOST = "http://localhost:";
    private final String PATH = "/api/answer";

    //Обертка над REST
    @Autowired
    private TestRestTemplate testRestTemplate;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private AnswerController answerController;
    private Answer answer;

    @BeforeEach
    void beforeEach(){
        Question question = new Question()
                .setName("Question Test")
                .setNumOfCorrect(1);
        answer = answerRepository.save(new Answer()
        		.setQuestion(question)
        		.setCorrect(true)
        		.setName("Test name"));
    }
    
    @Test
    void answerControllerIsNotNull() {
    	assertThat(answerController).isNotNull();
    }
    
    @Test
    void responseStatusOK() {
    	HttpStatus statusCode = testRestTemplate
    			.getForEntity(HOST+port+PATH+"/", String.class)
    			.getStatusCode();
    	assertThat(statusCode).isEqualTo(HttpStatus.OK);
    }
    
    @Test
    void responseStatusBADREQUEST() {
    	HttpStatus statusCode = testRestTemplate
    			.getForEntity(HOST+port+PATH+"/1000", String.class)
    			.getStatusCode();
    	assertThat(statusCode).isEqualTo(HttpStatus.BAD_REQUEST);
    }
    
    @Test
    void responseAnswer() {
    	Answer forObject = testRestTemplate
    			.getForObject(HOST+port+PATH+"/"+answer.getId(), Answer.class);
    	answer.setQuestion(null);
    	assertThat(forObject).isEqualTo(answer);
    }
    
    @AfterEach
    void afterEach() {
    	questionRepository.deleteAll();
    }
}