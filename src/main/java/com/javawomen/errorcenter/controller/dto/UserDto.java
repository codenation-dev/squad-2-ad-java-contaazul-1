package com.javawomen.errorcenter.controller.dto;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.javawomen.errorcenter.model.User;


//dados que irá para o cliente
public class UserDto {
	
	private Long id;	
    private String name;
    private String email;
    private LocalDateTime createdAt;
    
    //construtor que recebe um user completo
	public UserDto(User user) {
		this.id = user.getId();
		this.name = user.getName();
		this.email = user.getEmail();
		this.createdAt = user.getCreatedAt();
	}
	

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public String getEmail() {
		return email;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}
	
	
	
	// -------------- static ----------------------
	
    //retorna uma lista de Usuários sem a senha
	//public static List<UserDto> converter(List<User> users) {
	//	return users.stream().map(UserDto::new).collect(Collectors.toList());
	//}
	
	
	//retorna um Usuário sem a senha
	public static UserDto converterToUser(User user) {			
		return new UserDto(user);
	}
	
	//retorna uma lista de Usuários sem a senha
	public static Page<UserDto> converter(Page<User> users) {
		return users.map(UserDto::new);
	}

	public static UserDto converterToUser(Optional<User> userOptional) {
		//User user = userOptional.get();		
		return converterToUser(userOptional.get());
	}
    
    
}
