package com.javawomen.errorcenter.controller.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {

	private String email;
	
	private String passCode;
	

	public void setEmail(String email) {
		this.email = email;
	}

	public void setPassCode(String passCode) {
		this.passCode = passCode;
	}

	public String getEmail() {
		return email;
	}

	public String getPassCode() {
		return passCode;
	}

	public UsernamePasswordAuthenticationToken convert() {//metodo para o autentication manager
		return new UsernamePasswordAuthenticationToken(this.email, this.passCode);
	}
	
	
}
