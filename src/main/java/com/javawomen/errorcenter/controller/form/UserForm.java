package com.javawomen.errorcenter.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


public class UserForm {

	@NotBlank(message = "{name.not.blank}")
	@Size(min = 3, max = 100)
	private String name;

	@NotBlank(message = "{email.not.blank}")
	@Email(message = "{email.not.valid}")	
	private String email;

	@NotBlank(message = "{password.not.blank}")
	@Size(min = 8)
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
	
	//public String getRoleName() {
		//return roleName;
	//}

	//public User converter(RoleService roleService) {
		//Optional<Role> roleOptional = roleService.findByName(roleName);
		//if(!roleOptional.isPresent())throw new ResourceNotFoundException("Role não encontrado.");
		//return new User(this.name, new BCryptPasswordEncoder().encode(this.password), this.email, roleOptional.get());
	//}
	
	
}
