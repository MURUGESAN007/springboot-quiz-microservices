package com.mrk7.quiz_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrk7.quiz_service.dto.QuestionWrapper;
import com.mrk7.quiz_service.dto.QuizDto;
import com.mrk7.quiz_service.dto.Response;
import com.mrk7.quiz_service.service.QuizService;

@RestController
@RequestMapping("/api/v1/quizzes")
public class QuizController {

	@Autowired
	QuizService quizservice;
	
	@PostMapping("/questions/getquestionsforquiz")
	public ResponseEntity<String> createQuiz(@RequestBody QuizDto quizdto){
		return new ResponseEntity<String>(quizservice.createQuiz(quizdto.getCategoryName(), quizdto.getNumQ(), quizdto.getTitle()), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<List<QuestionWrapper>> getQuiz(@PathVariable int id){
		return quizservice.getQuiz(id);
	}
	
	@PostMapping("/submit/{id}")
	public ResponseEntity<Integer> submitQuiz(@PathVariable int id,@RequestBody List<Response> responses){
		return quizservice.submitQuiz(id, responses);
	}
}
