package com.javawomen.errorcenter.controller.form;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

import com.javawomen.errorcenter.model.Environment;
import com.javawomen.errorcenter.model.Level;
import com.javawomen.errorcenter.model.Log;
import com.javawomen.errorcenter.repository.EnvironmentRepository;
import com.javawomen.errorcenter.repository.LevelRepository;



public class LogForm {
	
	@NotNull @NotEmpty 
	private String nameLevel; //Level level;
	
	@NotNull @NotEmpty 
	private String nameEnvironment; //Environment environment;
	
	@NotNull @NotEmpty 
	private String origin;
	
	@NotNull @NotEmpty @Length(min = 3)
	private String description;
	

	
	public String getNameLevel() {
		return nameLevel;
	}



	public void setNameLevel(String nameLevel) {
		this.nameLevel = nameLevel;
	}



	public String getNameEnvironment() {
		return nameEnvironment;
	}



	public void setNameEnvironment(String nameEnvironment) {
		this.nameEnvironment = nameEnvironment;
	}



	public String getOrigin() {
		return origin;
	}



	public void setOrigin(String origin) {
		this.origin = origin;
	}



	public String getDescription() {
		return description;
	}



	public void setDescription(String description) {
		this.description = description;
	}



	public Log converter(LevelRepository levelRepository, EnvironmentRepository environmentRepository) {
		
		Level level = levelRepository.findByName(nameLevel);
		
		Environment environment = environmentRepository.findByName(nameEnvironment);
		
		return new Log(level, environment, origin, description);
	}
	
}
