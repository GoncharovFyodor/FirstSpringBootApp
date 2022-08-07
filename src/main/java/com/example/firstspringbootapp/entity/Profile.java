package com.example.firstspringbootapp.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name="Profiles")
@Accessors(chain = true)
public class Profile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(nullable = false)
    private String name;
    @OneToMany(cascade=CascadeType.ALL,mappedBy="profile")
    @JsonManagedReference(value="question-profile")
    private List<Question> questions;
}
