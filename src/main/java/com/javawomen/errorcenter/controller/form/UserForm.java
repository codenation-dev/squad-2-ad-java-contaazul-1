package com.javawomen.errorcenter.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.javawomen.errorcenter.model.User;


public class UserForm {
	
	@NotNull(message="{name.not.null}")
	@NotEmpty(message="{name.not.empty}")
	@NotBlank(message="{name.not.blank}")
	@Length(min=3,max=50)
    private String name;
	
	@NotNull(message="{email.not.null}")
	@NotEmpty(message="{email.not.empty}")
	@Email(message="{email.not.valid}")
	@NotBlank(message="{email.not.blank}")
	@Length(min=9,max=100)
    private String email;
	
	@NotNull(message="{name.not.null}")
	@NotEmpty(message="{name.not.empty}")
	@NotBlank(message="{name.not.blank}")
	@Length(min = 6,max=100)
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
