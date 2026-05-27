package com.mrk7.quiz_service.dto;

public class QuizDto {
	
	private String categoryName;
	private String title;
	private int numQ;
	public String getCategoryName() {
		return categoryName;
	}
	public void setCategoryName(String categoryName) {
		this.categoryName = categoryName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getNumQ() {
		return numQ;
	}
	public void setNumQ(int numQ) {
		this.numQ = numQ;
	}
	public QuizDto(String categoryName, String title, int numQ) {
		super();
		this.categoryName = categoryName;
		this.title = title;
		this.numQ = numQ;
	}
	public QuizDto() {
		super();
	}

	
}
