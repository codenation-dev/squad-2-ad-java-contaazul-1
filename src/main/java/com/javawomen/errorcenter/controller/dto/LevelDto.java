package com.javawomen.errorcenter.controller.dto;

import com.javawomen.errorcenter.model.Level;

public class LevelDto {
	
	private Long id;
	private String name;
	
	public LevelDto(Level level) {
		this.id = level.getId();
		this.name = level.getName();
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
