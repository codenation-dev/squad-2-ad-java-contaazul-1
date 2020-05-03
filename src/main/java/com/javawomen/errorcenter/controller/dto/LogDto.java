package com.javawomen.errorcenter.controller.dto;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;

import com.javawomen.errorcenter.model.Log;

public class LogDto {
	
	private Long id;
    private LocalDateTime createdAt; 
	private String levelName;
	private String environmentName;//Environment environment;
	private String origin;
	private String description;
	
	
	public LogDto(Log log) {
		super();
		this.id = log.getId();
		this.createdAt = log.getCreatedAt();
		this.levelName = log.getLevel().getName();
		this.environmentName = log.getEnvironment().getName();
		this.origin = log.getOrigin();
		this.description = log.getDescription();
	}
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public String getLevelName() {
		return levelName;
	}

	public void setLevelName(String levelName) {
		this.levelName = levelName;
	}

	public String getEnvironmentName() {
		return environmentName;
	}

	public void setEnvironmentName(String environmentName) {
		this.environmentName = environmentName;
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


	// -------------- static ----------------------
	
	//retorna uma lista em paginas de Logs 
	public static Page<LogDto> converter(Page<Log> logs) {
		//return logs.stream().map(LogDto::new).collect(Collectors.toList());
		return logs.map(LogDto::new);
	}
	
	//retorna um log (para nao devolvar uma entidade)
	public static List<LogDto> converterToLog(List<Log> logs) {			
		//return new LogDto(log);
		return logs.stream().map(LogDto::new).collect(Collectors.toList());
	}

	public static LogDto converterToLog(Optional<Log> logOptional) {
		// TODO Auto-generated method stub
		//return converterToLog(userOptional.get());
		return new LogDto(logOptional.get());
	}

}
