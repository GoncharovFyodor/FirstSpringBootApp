package com.example.firstspringbootapp.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "Questions")
@Accessors(chain = true)
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false, unique = true)
    private String name;
    @Column(nullable = false)
    private Integer numOfCorrect;

    @ManyToOne(cascade =
            {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
    @JoinColumn(name = "profile_id")
    @JsonBackReference(value="question-profile")
    private Profile profile;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "level_id")
    @JsonBackReference(value="question-level")
    private Level level;

    @OneToMany(mappedBy = "question", cascade =
            CascadeType.ALL)
    @JsonManagedReference(value="question-answer")
    private List<Answer> answers;

    public void addAnswerToQuestion(Answer answer){
        if (answers==null) answers = new ArrayList<>();
        answer.setQuestion(this);
        answers.add(answer);
    }
}
