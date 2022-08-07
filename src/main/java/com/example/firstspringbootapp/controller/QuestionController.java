package com.example.firstspringbootapp.controller;

import com.example.firstspringbootapp.advice.QuestionRepresentationModel;
import com.example.firstspringbootapp.entity.Question;
import com.example.firstspringbootapp.service.QuestionService;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/question")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;
    private final QuestionRepresentationModel model;

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Question>> findById(@PathVariable Integer id){
        EntityModel<Question> questionModel = model.toModel(questionService.findBy(id));
        return ResponseEntity.ok().body(questionModel);
    }

    @GetMapping("/")
    public ResponseEntity<List<Question>> findAll(){
        return ResponseEntity.ok().body(questionService.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<String> save(@RequestBody Question question){
        questionService.save(question);
        return ResponseEntity.ok("Question is added");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody Question question, @PathVariable Integer id){
        questionService.update(question,id);
        return ResponseEntity.ok("Question is updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
        questionService.delete(id);
        return ResponseEntity.ok("Question is deleted");
    }
}
