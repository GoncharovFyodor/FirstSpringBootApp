package com.example.firstspringbootapp.service;

import com.example.firstspringbootapp.entity.Answer;
import com.example.firstspringbootapp.entity.Question;
import com.example.firstspringbootapp.exception.AnswerNotFoundException;
import com.example.firstspringbootapp.repository.AnswerRepository;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.testcontainers.containers.PostgreSQLContainer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
@TestPropertySource(locations="classpath:test.properties")
class AnswerServiceImplTest {
    static final PostgreSQLContainer<?> postgresContainer;
    static{
        postgresContainer = new PostgreSQLContainer<>("postgres:12")
                .withDatabaseName("rest-spring-boot")
                .withUsername("postgres")
                .withPassword("postgres");
    }
    @Mock
    private AnswerRepository answerRepository;
    @InjectMocks
    private AnswerServiceImpl answerService;

    private Answer answer;
    private Answer answer1;
    private List<Answer> answers;

    @BeforeEach
    public void initAnswer(){
        answer = new Answer()
                .setQuestion(new Question())
                .setCorrect(true)
                .setName("Test answer");
        answer1 = new Answer()
        		.setQuestion(new Question())
                .setCorrect(true)
                .setName("Test answer2");
        answers=new ArrayList<>();
        answers.add(answer);
        answers.add(answer1);
    }
    @DynamicPropertySource
    static void sourceConfiguration(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
        registry.add("spring.datasource.username", postgresContainer::getUsername);
        registry.add("spring.datasource.password", postgresContainer::getUsername);
    }
    @Test
    void AnswerIsNotNullIfTheyAreOnDatabase() {
        Mockito.doReturn(answers).when(answerRepository).findAll();
        List<Answer> all = answerService.findAll();
        assertThat(all).isNotEmpty().hasSize(2);
    }

    @Test
    void AnswerIsNotEmptyIfFoundOnDatabase() {
        Mockito.doReturn(Optional.of(answer)).when(answerRepository).findById(answer.getId());
        Answer respAnswer = answerService.findBy(answer.getId());
        assertThat(respAnswer).isNotNull();
    }

    @Test
    void checkingSavedAnswerWithReceivedFromDb() {
        Mockito.doReturn(Optional.of(answer)).when(answerRepository).findById(answer.getId());
        Answer respAnswer = answerService.findBy(answer.getId());
        assertThat(respAnswer).isEqualTo(answer);
    }

    @Tag("AnswerThrow")
    @Nested
    class ThrowException{
        @Test
        void throwExceptionIfThereAreNoAnswersInDb(){
            Mockito.doReturn(List.of()).when(answerRepository).findAll();
            assertAll(()->
                    assertThat(
                            assertThrows(AnswerNotFoundException.class,
                                ()->answerService.findAll()).getMessage())
                            .isEqualTo("No answers found in database"));
        }
        @Test
        void throwAnswerNotFoundExceptionIfNoRepliesFoundById(){
            Mockito.doReturn(Optional.empty()).when(answerRepository).findById(answer.getId());
            assertAll(()->
                    assertThat(
                            assertThrows(AnswerNotFoundException.class,
                                    ()->answerService.findBy(answer.getId())).getMessage())
                            .isEqualTo("Answer with id null not found"));
        }
    }
}