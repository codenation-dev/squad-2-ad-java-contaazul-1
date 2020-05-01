package com.javawomen.errorcenter.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

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
	
	//getter

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
	
    //retorna uma lista de Usuários sem a senha
	public static List<UserDto> converter(List<User> users) {
		return users.stream().map(UserDto::new).collect(Collectors.toList());
	}
	
	//static
	//retorna um Usuário sem a senha
	public static UserDto converterToUser(User user) {			
		return new UserDto(user);
	}
    
    
}
