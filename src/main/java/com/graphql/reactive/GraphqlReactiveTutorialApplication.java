package com.graphql.reactive;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.graphql.reactive.entity.Student;

import reactor.core.publisher.Sinks;
import reactor.core.publisher.Sinks.Many;

@SpringBootApplication
public class GraphqlReactiveTutorialApplication {

	public static void main(String[] args) {
		SpringApplication.run(GraphqlReactiveTutorialApplication.class, args);
	}
	
	@Bean
	public Many<Student> studentSinkConfig() {
		return Sinks.many().multicast().directBestEffort();
	}

}
