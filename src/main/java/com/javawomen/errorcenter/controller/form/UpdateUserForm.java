package com.javawomen.errorcenter.controller.form;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;


//deletar essa classe apos os testes

public class UpdateUserForm {

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

	//private String roleName;
	
	
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
	
	
	//public User converter() {
		//return new User(this.name, new BCryptPasswordEncoder().encode(this.password), this.email, ...);
	//}
	
	//public User converter(UserRepository userRepository) {
	//	return new User(name, email, password);
	//}


	//public User update(Long id, UserRepository userRepository) {
	//	User user = userRepository.getOne(id);
	//
	//		user.setName(this.name);
	//	user.setEmail(this.email);
	//	user.setPassword(this.password);
	//
	//	return user;
	//}

	/*//usado para mudar a senha, email, nome do user logado
	public User update(Long id, UserService userService) {
		User user = userService.getOne(id);
		user.setName(this.name);
		user.setEmail(this.email);
		user.setPassword(new BCryptPasswordEncoder().encode(this.password));
		return user;
	}
	
	//usado para mudar o perfil do user 
	public User updateRole(Long id, UserService userService, RoleService roleService) {		
		User user = userService.getOne(id);
		Optional<Role> roleOptional = roleService.findByName(this.roleName);
		if(!roleOptional.isPresent())throw new ResourceNotFoundException("Role não encontrado.");
		user.setRoles(roleOptional.get());
		return user;
	}
*/



}
