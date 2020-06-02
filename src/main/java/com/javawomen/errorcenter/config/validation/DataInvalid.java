package com.javawomen.errorcenter.config.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
 
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class DataInvalid extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public DataInvalid(String message) {
		super(message);
	}
}
