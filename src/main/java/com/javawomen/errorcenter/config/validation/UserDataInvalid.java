package com.javawomen.errorcenter.config.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;
 
@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserDataInvalid extends RuntimeException {

	private static final long serialVersionUID = 1L;
	//Os dados enviados também fazem parte da requisição, se eles estiverem inválidos retorna um código 400 (bad request), que indica uma má requisição.
	public UserDataInvalid(String message) {
		super(message);
	}
}
