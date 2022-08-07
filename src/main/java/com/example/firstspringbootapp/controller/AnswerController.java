package com.example.firstspringbootapp.controller;

import java.util.List;

import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.firstspringbootapp.advice.AnswerRepresentationModel;
import com.example.firstspringbootapp.advice.QuestionRepresentationModel;
import com.example.firstspringbootapp.entity.Answer;
import com.example.firstspringbootapp.service.AnswerService;
import com.example.firstspringbootapp.service.QuestionService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/answer")
@RequiredArgsConstructor
public class AnswerController {
	private final AnswerService answerService;
    private final AnswerRepresentationModel model;

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<Answer>> findById(@PathVariable Integer id){
        EntityModel<Answer> answerModel = model.toModel(answerService.findBy(id));
        return ResponseEntity.ok().body(answerModel);
    }

    @GetMapping("/")
    public ResponseEntity<List<Answer>> findAll(){
        return ResponseEntity.ok().body(answerService.findAll());
    }

    @PostMapping("/")
    public ResponseEntity<String> save(@RequestBody Answer answer){
    	answerService.save(answer);
        return ResponseEntity.ok("Answer is added");
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> update(@RequestBody Answer answer, @PathVariable Integer id){
    	answerService.update(answer,id);
        return ResponseEntity.ok("Answer is updated");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable Integer id){
    	answerService.delete(id);
        return ResponseEntity.ok("Answeris deleted");
    }
}
