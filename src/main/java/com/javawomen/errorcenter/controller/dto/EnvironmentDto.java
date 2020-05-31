package com.javawomen.errorcenter.controller.dto;


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
	
}
