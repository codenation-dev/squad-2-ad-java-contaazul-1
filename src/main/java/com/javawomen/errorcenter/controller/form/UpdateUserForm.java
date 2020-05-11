package com.javawomen.errorcenter.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.javawomen.errorcenter.model.User;
import com.javawomen.errorcenter.repository.UserRepository;


public class UpdateUserForm {
	
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
	
	@NotNull(message="{password.not.null}")
	@NotEmpty(message="{password.not.empty}")
	@NotBlank(message="{password.not.blank}")
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
	
	public User converter(UserRepository userRepository) {
		return new User(name, email, password);
	}

	public User update(Long id, UserRepository userRepository) {
		User user = userRepository.getOne(id);
		
		user.setName(this.name);
		user.setEmail(this.email);
		user.setPassword(this.password);
		
		return user;
	}
	
}
