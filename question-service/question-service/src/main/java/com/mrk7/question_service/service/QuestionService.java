package com.mrk7.question_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.mrk7.question_service.dto.QuestionWrapper;
import com.mrk7.question_service.dto.Response;
import com.mrk7.question_service.exception.ResourceNotFoundException;
import com.mrk7.question_service.model.Question;
import com.mrk7.question_service.repository.QuestionRepository;


@Service
public class QuestionService {

	@Autowired
	private QuestionRepository questionrepo;
	
	public Question getQuestionById(int id) {
		return questionrepo.findById(id).get();
	}
	
	public List<Question> getAllQuestion(){
		return questionrepo.findAll();
	}

	public String addQuestion(Question q) {
		questionrepo.save(q);
		return "Question Added Successfully";
	}
	
	public List<Question> getByCategory(String category){
		return questionrepo.findByCategory(category);
	}
	
	public String deleteById(int id){
		Question q = questionrepo.findById(id).orElseThrow(()-> new ResourceNotFoundException(" Question ", " ID ", id));
		questionrepo.delete(q);
		
		return "Question Deleted Successfully";
	}
	
	public String updateById(int id,Question q){
		Question db_q = questionrepo.findById(id).orElseThrow(()-> new ResourceNotFoundException(" Question ", " ID ", id));
		
		db_q.setCategory(q.getCategory());
		db_q.setDifficultylevel(q.getDifficultylevel());
		db_q.setOption1(q.getOption1());
		db_q.setOption2(q.getOption2());
		db_q.setOption3(q.getOption3());
		db_q.setOption4(q.getOption4());
		db_q.setQuestionTitle(q.getQuestionTitle());
		db_q.setRightAnswer(q.getRightAnswer());
		
		questionrepo.save(db_q);
		
		return "Question Updated Successfully";
		
	}
	
	public List<Integer> getQuestionsForQuiz(String categoryName,int numQ){
		List<Integer> questions = questionrepo.findRandomQuestionByCategory(categoryName, numQ);
		
		return questions;
	}
	
	public List<QuestionWrapper> getQuestionsFromId(@RequestBody List<Integer> questionIds){
		
		List<Question> questions = new ArrayList<Question>();
		List<QuestionWrapper> wrappers = new ArrayList<QuestionWrapper>();
		
		for(int question : questionIds) {
			questions.add(questionrepo.findById(question).get());
		}
		
		for(Question question : questions) {
			QuestionWrapper wrapper = new QuestionWrapper(question.getId(), question.getQuestionTitle(), question.getOption1(), question.getOption2(), question.getOption3(), question.getOption4());
			wrappers.add(wrapper);
		}
		return wrappers;
	}
	
	public Integer getScore(List<Response> responses) {
		int right = 0;
		for(Response response : responses) {
			Question question = questionrepo.findById(response.getId()).get();
			if(response.getResponse().equals(question.getRightAnswer())) {
				right++;
			}
		}
		return right;
	}
	
	
}
