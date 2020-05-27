package com.javawomen.errorcenter.controller.form;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

//deletar essa classe apos os testes
public class UpdateUserForm {

	@NotBlank(message = "{name.not.blank}")
	@Size(min = 3, max = 100)
	private String name;

	@NotBlank(message = "{email.not.blank}")
    @Email(message = "{email.not.valid}")
    @Column(name = "email")
	private String email;

	@NotBlank(message = "{password.not.blank}")
	@Size(min = 8, max = 255)
	private String password;
	
	
	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
}
