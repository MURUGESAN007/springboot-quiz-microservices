package com.mrk7.quiz_service.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.mrk7.quiz_service.dto.QuestionWrapper;
import com.mrk7.quiz_service.dto.Response;

@FeignClient("QUESTION-SERVICE")
public interface QuizInterface {

	@GetMapping("/api/v1/questions/generatequiz/{categoryName}/{numQ}")
	public ResponseEntity<List<Integer>> getQuestionsForQuiz(@PathVariable("categoryName") String categoryName,@PathVariable("numQ") int numQ);
	
	@PostMapping("/api/v1/questions/getQuestions")
	public ResponseEntity<List<QuestionWrapper>> getQuestionsFromIds(@RequestBody List<Integer> questionIds);
	
	@PostMapping("/api/v1/questions/getScore")
	public ResponseEntity<Integer> getScore(@RequestBody List<Response> responses);
}
