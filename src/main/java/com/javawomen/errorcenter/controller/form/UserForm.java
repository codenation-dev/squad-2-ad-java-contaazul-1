package com.javawomen.errorcenter.controller.form;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.javawomen.errorcenter.model.User;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


//dados que chegam do cliente
public class UserForm {
	
	@NotNull @NotEmpty @Length(min = 2)
    private String name;
	
	@NotNull @NotEmpty @Length(min = 5)
    private String email;
	
	@NotNull @NotEmpty @Length(min = 5)
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
	
	public User converter() {
		return new User(name, email, password);
	}
	
	// verificar isso 
	//public User converter() {
    //return new User(this.name, new BCryptPasswordEncoder().encode(this.password), this.email);
  	//}
}
