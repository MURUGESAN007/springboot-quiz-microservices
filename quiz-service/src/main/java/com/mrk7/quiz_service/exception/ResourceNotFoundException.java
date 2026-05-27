package com.mrk7.quiz_service.exception;

public class ResourceNotFoundException extends RuntimeException {

	private String fieldName;
	private String resourceName;
	private int fieldId;
	
	public ResourceNotFoundException(String fieldName, String resourceName, int fieldId) {
		super();
		this.fieldName = fieldName;
		this.resourceName = resourceName;
		this.fieldId = fieldId;
	}
	
	@Override
	public String getMessage() {
		return resourceName +" not Found For " + fieldName + " = " + fieldId;
	}
	
}
