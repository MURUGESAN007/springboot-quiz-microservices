package com.mrk7.quiz_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.mrk7.quiz_service.dto.QuestionWrapper;
import com.mrk7.quiz_service.dto.Response;
import com.mrk7.quiz_service.exception.ResourceNotFoundException;
import com.mrk7.quiz_service.feign.QuizInterface;
import com.mrk7.quiz_service.model.Quiz;
import com.mrk7.quiz_service.repository.QuizRepository;

@Service
public class QuizService {

	@Autowired
	QuizRepository quizrepo;
	
	@Autowired
	QuizInterface quizInterface;
	
	public String createQuiz(String category, int numQ, String title) {
		
		List<Integer> questions = quizInterface.getQuestionsForQuiz(category, numQ).getBody();
		
		Quiz quiz = new Quiz();
		quiz.setTitle(title);
		quiz.setQuestionIds(questions);
		quizrepo.save(quiz);
		
		return "Quiz Created Successfully";
	}
	
	public ResponseEntity<List<QuestionWrapper>> getQuiz(int id){
		
		Quiz quiz = quizrepo.findById(id).get();
		
		List<Integer> questionIds = quiz.getQuestionIds();
		
		ResponseEntity<List<QuestionWrapper>> questions = quizInterface.getQuestionsFromIds(questionIds);
		
		return questions;
	}
	
	public ResponseEntity<Integer> submitQuiz(int id,List<Response> responses){
		ResponseEntity<Integer> score = quizInterface.getScore(responses);
		return score;
	}
	
	
}
