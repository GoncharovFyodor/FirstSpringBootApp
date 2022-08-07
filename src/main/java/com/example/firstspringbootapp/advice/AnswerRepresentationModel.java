package com.example.firstspringbootapp.advice;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Component;

import com.example.firstspringbootapp.controller.AnswerController;
import com.example.firstspringbootapp.entity.Answer;

@Component
public class AnswerRepresentationModel implements RepresentationModelAssembler<Answer, EntityModel<Answer>> {

	@Override
	public EntityModel<Answer> toModel(Answer entity) {
		return EntityModel.of(entity,
                linkTo(methodOn(AnswerController.class).delete(entity.getId()))
                        .withSelfRel().withRel("HTTP method "+ HttpMethod.DELETE).expand(),
                linkTo(methodOn(AnswerController.class).findAll())
                        .withSelfRel().withRel("HTTP method "+ HttpMethod.GET).expand(),
                linkTo(methodOn(AnswerController.class).save(entity))
                        .withSelfRel().withRel("HTTP method "+ HttpMethod.POST).expand(),
                linkTo(methodOn(AnswerController.class).update(entity, entity.getId()))
                        .withSelfRel().withRel("HTTP method "+ HttpMethod.PUT).expand()
        );
	}

}
