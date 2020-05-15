package com.javawomen.errorcenter.controller.dto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.javawomen.errorcenter.model.Environment;
 
public class EnvironmentDto {
	
	private Long id;
	private String name;
	
	public EnvironmentDto(Environment environment) {
		this.id = environment.getId();
		this.name = environment.getName();
	}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//--------------- static ------------
	
	public static EnvironmentDto converterToEnvironment(Environment environment) {			
		return new EnvironmentDto(environment);
	}
	
	public static List<EnvironmentDto> converter(List<Environment> environments) {
		return environments.stream().map(EnvironmentDto::new).collect(Collectors.toList());
	}

	public static EnvironmentDto converterToEnvironment(Optional<Environment> environmentOptional) {
		return converterToEnvironment(environmentOptional.get());
	}
	
}
