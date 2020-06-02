package com.javawomen.errorcenter.config.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
 
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class ErrorInternal extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ErrorInternal(String message) {
		super(message);
	}
}
