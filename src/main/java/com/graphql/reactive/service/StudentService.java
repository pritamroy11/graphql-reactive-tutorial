package com.graphql.reactive.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graphql.reactive.entity.Student;
import com.graphql.reactive.repository.StudentRepository;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class StudentService {
	
	@Autowired
	StudentRepository repository;
	
	public Flux<Student> getAllStudents() {
		return repository.findAll();
	}
	
	public Mono<Student> addStudent(Mono<Student> student) {
		return student.flatMap(repository :: insert);
	}

}
