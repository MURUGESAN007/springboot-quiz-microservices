package com.mrk7.question_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.mrk7.question_service.model.Question;


public interface QuestionRepository extends JpaRepository<Question, Integer> {
	
	public List<Question> findByCategory(String category);

	@Query(value = "SELECT q.id FROM question q WHERE q.category = :category ORDER BY RAND() LIMIT :numQ", nativeQuery = true)
	public List<Integer> findRandomQuestionByCategory(String category, int numQ);
	
}
