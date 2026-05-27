package com.mrk7.quiz_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mrk7.quiz_service.model.Quiz;


public interface QuizRepository extends JpaRepository<Quiz, Integer>{

}
