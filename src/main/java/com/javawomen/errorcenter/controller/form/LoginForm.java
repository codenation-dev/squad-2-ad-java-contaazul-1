package com.javawomen.errorcenter.controller.form;

import javax.validation.constraints.NotBlank;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class LoginForm {
	
	@NotBlank(message = "{name.not.blank}")
	private String email;
	
	@NotBlank(message = "{name.not.blank}")
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

	public UsernamePasswordAuthenticationToken convert() {
		return new UsernamePasswordAuthenticationToken(this.email, this.senha);
	}
	
	
}
