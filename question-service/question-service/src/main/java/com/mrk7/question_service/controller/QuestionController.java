package com.mrk7.question_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mrk7.question_service.dto.QuestionWrapper;
import com.mrk7.question_service.dto.Response;
import com.mrk7.question_service.model.Question;
import com.mrk7.question_service.service.QuestionService;

@RestController
@RequestMapping("/api/v1/questions")
public class QuestionController {

	@Autowired
	private QuestionService questionservice;

	@PostMapping
	public ResponseEntity<String> addQuestion(@RequestBody Question q){
		return new ResponseEntity(questionservice.addQuestion(q), HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Question> getQuestionById(@PathVariable int id){
		return new ResponseEntity<Question>(questionservice.getQuestionById(id), HttpStatus.OK);
	}
	
	@GetMapping
	public ResponseEntity<List<Question>> getAllQuestion(){
		List<Question> ques = questionservice.getAllQuestion();
		return new ResponseEntity<List<Question>>(ques, HttpStatus.OK);
	}
	
	@GetMapping("/getByCategory/{category}")
	public ResponseEntity<List<Question>> getByCategory(@PathVariable String category){
		List<Question> ques = questionservice.getByCategory(category);
		return new ResponseEntity<List<Question>>(ques, HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteQuetionById(@PathVariable int id){
		return new ResponseEntity(questionservice.deleteById(id), HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<String> updateQuestionById(@PathVariable int id,@RequestBody Question q){
		return new ResponseEntity(questionservice.updateById(id, q), HttpStatus.CREATED);
	}
	
	@GetMapping("/generatequiz/{categoryName}/{numQ}")
	public ResponseEntity<List<Integer>> generateQuiz(@PathVariable String categoryName,@PathVariable int numQ){
		List<Integer> questions = questionservice.getQuestionsForQuiz(categoryName, numQ);
		return new ResponseEntity<List<Integer>>(questions, HttpStatus.OK);
	}
	
	@PostMapping("/getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(@RequestBody List<Integer> questionIds){
		List<QuestionWrapper> wrappers = questionservice.getQuestionsFromId(questionIds);
		return new ResponseEntity<List<QuestionWrapper>>(wrappers, HttpStatus.OK);
	}
	
	@PostMapping("/getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses){
		return new ResponseEntity<Integer>(questionservice.getScore(responses), HttpStatus.OK);
	}
}
