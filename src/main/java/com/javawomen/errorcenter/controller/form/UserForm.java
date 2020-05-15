package com.javawomen.errorcenter.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class UserForm {

	@NotNull(message = "{name.not.null}")
	@NotEmpty(message = "{name.not.empty}")
	@NotBlank(message = "{name.not.blank}")
	@Length(min = 3, max = 50)
	private String name;

	@NotBlank(message = "{email.not.blank}")
	@NotNull(message = "{email.not.null}")
	@NotEmpty(message = "{email.not.empty}")
	@Email(message = "{email.not.valid}")	
	private String email;

	@NotNull(message = "{password.not.null}")
	@NotEmpty(message = "{password.not.empty}")
	@NotBlank(message = "{password.not.blank}")
	@Length(min = 8)
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
