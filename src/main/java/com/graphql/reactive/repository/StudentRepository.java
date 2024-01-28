package com.graphql.reactive.repository;

import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.stereotype.Repository;

import com.graphql.reactive.entity.Student;

@Repository
public interface StudentRepository extends ReactiveMongoRepository<Student, String>{

}
