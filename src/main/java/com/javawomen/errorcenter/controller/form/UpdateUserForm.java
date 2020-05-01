package com.javawomen.errorcenter.controller.form;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.javawomen.errorcenter.model.User;
import com.javawomen.errorcenter.repository.UserRepository;



public class UpdateUserForm {
	
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
