package com.example.entityrelation;

import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;
import java.util.Optional;
import java.util.UUID;

@EnableJpaAuditing
@SpringBootApplication
public class EntityrelationApplication {

	public static void main(String[] args) {
		SpringApplication.run(EntityrelationApplication.class, args);
	}

	@Bean
	public AuditorAware<String> auditorProviver(){
		return () -> Optional.of(UUID.randomUUID().toString());
	}
}
