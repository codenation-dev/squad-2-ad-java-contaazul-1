package com.javawomen.errorcenter.controller.form;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {

	private String email;
	
	private String senha;
	

	public void setEmail(String email) {
		this.email = email;
	}

	public void setSenha(String password) {
		this.senha = password;
	}

	public String getEmail() {
		return email;
	}

	public String getSenha() {
		return senha;
	}

	public UsernamePasswordAuthenticationToken convert() {//metodo para o autentication manager
		return new UsernamePasswordAuthenticationToken(this.email, this.senha);
	}
	
	
}
