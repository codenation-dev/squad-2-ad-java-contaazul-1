package com.javawomen.errorcenter.config.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND) // erro 404; ver adiferença entre code e value
public class ResourceNotFoundException extends RuntimeException { // para nao ser uma exception tipo checked

	private static final long serialVersionUID = 1L;

	public ResourceNotFoundException(String message) {
		super(message);
	}
}
