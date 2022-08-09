package com.example.firstspringbootapp.controller;

import com.example.firstspringbootapp.entity.Answer;
import com.example.firstspringbootapp.entity.Question;
import com.example.firstspringbootapp.repository.QuestionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.hateoas.MediaTypes;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.lifecycle.Startables;
import org.testcontainers.utility.DockerImageName;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
//@WebMvcTest(QuestionController.class)
@TestPropertySource(locations="classpath:test.properties")
public class QuestionControllerIT {
    static final PostgreSQLContainer<?> postgresContainer;
    static{
        postgresContainer = new PostgreSQLContainer<>("postgres:12")
                .withDatabaseName("rest-spring-boot")
                .withUsername("postgres")
                .withPassword("postgres");
    }
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private QuestionRepository questionRepository;
    private Question question;

    //@MockBean
    //private QuestionService questionService;
    //@MockBean
    //private QuestionRepository questionRepository;
    //@MockBean
    //private QuestionRepresentationModel questionRepresentationModel;

    @BeforeEach
    void beforeEach() {
        questionRepository.deleteAll();
        Answer answer = new Answer()
                .setCorrect(true)
                .setName("Testing answer for IT");
        Question saveQuestion = new Question()
                .setName("Testing question for IT")
                .setNumOfCorrect(1);
        saveQuestion.addAnswerToQuestion(answer);
        question = questionRepository.save(saveQuestion);
    }
    @DynamicPropertySource
    static void sourceConfiguration(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getUsername);
    }
    @Test
    void callQuestionControllerMethodGetByIdShouldReturnResponseWithStatusOK() throws Exception{
        //Mockito.doReturn(Optional.of(question)).when(questionRepository).findById(question.getId());
        mockMvc.perform(get("/question/"+question.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON));
    }

    @Test
    void callQuestionControllerMethodGetByIdShouldReturnQuestionEntity() throws Exception{
        mockMvc.perform(get("/question/"+question.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaTypes.HAL_JSON))
                .andExpect(jsonPath("id").value(question.getId()))
                .andExpect(jsonPath("name").value(question.getName()))
                .andExpect(jsonPath("numOfCorrect").value(question.getNumOfCorrect()))
                .andExpect(jsonPath("answers").isNotEmpty());
    }
}
