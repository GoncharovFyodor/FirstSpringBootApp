package com.example.firstspringbootapp.advice;

import com.example.firstspringbootapp.controller.QuestionController;
import com.example.firstspringbootapp.entity.Question;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class QuestionRepresentationModel implements RepresentationModelAssembler<Question, EntityModel<Question>> {
    @Override
    public EntityModel<Question> toModel(Question entity) {
        return EntityModel.of(entity,
                linkTo(methodOn(QuestionController.class).delete(entity.getId()))
                        .withSelfRel().withRel("HTTP method "+ HttpMethod.DELETE).expand(),
                linkTo(methodOn(QuestionController.class).findAll())
                        .withSelfRel().withRel("HTTP method "+ HttpMethod.GET).expand(),
                linkTo(methodOn(QuestionController.class).save(entity))
                        .withSelfRel().withRel("HTTP method "+ HttpMethod.POST).expand(),
                linkTo(methodOn(QuestionController.class).update(entity, entity.getId()))
                        .withSelfRel().withRel("HTTP method "+ HttpMethod.PUT).expand()
        );
    }
}
