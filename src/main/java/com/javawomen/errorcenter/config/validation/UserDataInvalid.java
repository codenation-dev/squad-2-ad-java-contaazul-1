package com.javawomen.errorcenter.config.validation;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

//Se pensarmos que os dados enviados também fazem parte da requisição, se eles estiverem inválidos e, 
//portanto errados, nada mais justo que retornar um código 400 (bad request), que indica uma má requisição.


@ResponseStatus(HttpStatus.BAD_REQUEST)
public class UserDataInvalid extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public UserDataInvalid(String message) {
		super(message);
	}
}
