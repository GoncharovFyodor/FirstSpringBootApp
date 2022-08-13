package com.example.firstspringbootapp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="Levels")
@Accessors(chain = true)
public class Level {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @OneToMany(mappedBy = "level",cascade=CascadeType.ALL)
    @JsonManagedReference(value="question-level")
    private List<Question> questions;
}
