package com.javawomen.errorcenter.controller.dto;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.javawomen.errorcenter.model.Level;

public class LevelDto {
	
	private Long id;
	private String name;
	
	//form nao precisa de construtor
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
	
	//--------------- static foram passados para o service: retiarar apos testes  ------------
	/*	
	public static LevelDto converterToLevel(Optional<Level> levelOptional) {
		return converterToLevel(levelOptional.get());
	}
	
	public static LevelDto converterToLevel(Level level) {			
		return new LevelDto(level);
	}
	
	public static List<LevelDto> converter(List<Level> levels) {
		return levels.stream().map(LevelDto::new).collect(Collectors.toList());
	}
	*/
	
}
