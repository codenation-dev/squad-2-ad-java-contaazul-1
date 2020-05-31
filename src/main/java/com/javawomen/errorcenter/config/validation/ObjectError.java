package com.javawomen.errorcenter.config.validation;

public class ObjectError {
	
    private final String message;
    private final String field;
    private final Object parameter;
     
	public ObjectError(String message, String field, Object parameter) {
		this.message = message;
		this.field = field;
		this.parameter = parameter;
	}

	public String getMessage() {
		return message;
	}

	public String getField() {
		return field;
	}

	public Object getParameter() {
		return parameter;
	}
	
	
    
}
