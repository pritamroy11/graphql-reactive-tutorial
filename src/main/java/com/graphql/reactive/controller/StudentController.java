package com.graphql.reactive.controller;

import org.reactivestreams.Publisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.graphql.data.method.annotation.SubscriptionMapping;
import org.springframework.stereotype.Controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.graphql.reactive.entity.Student;
import com.graphql.reactive.request.StudentInput;
import com.graphql.reactive.service.StudentService;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Sinks.Many;

@Controller
public class StudentController {
	
	@Autowired
	StudentService service;
	
	@Autowired
	ObjectMapper mapper;
	
	@Autowired
	private Many<Student> studentSink;
	
	@QueryMapping("getAllStudents")
	public Flux<Student> getAll() {
		return service.getAllStudents();
	}
	
	@MutationMapping("addStudent")
	public Mono<Student> addStudent(@Argument("input") StudentInput input) {
		Student student = mapper.convertValue(input, Student.class);
		return service.addStudent(Mono.just(student)).doOnNext(studentSink :: tryEmitNext);
	}
	
	@SubscriptionMapping
	public Publisher<Student> studentSubscription() {
		System.out.println("Student Subscription done");
		System.out.println("Current count :: "+studentSink.currentSubscriberCount());
		return studentSink.asFlux();
	}
	
	
}
