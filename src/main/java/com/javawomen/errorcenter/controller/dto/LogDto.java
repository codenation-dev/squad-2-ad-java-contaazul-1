package com.javawomen.errorcenter.controller.dto;
 
import java.time.LocalDateTime;

import com.javawomen.errorcenter.model.Log;

public class LogDto {
	
	private Long id;
    private LocalDateTime createdAt; 
	private String levelName;
	private String environmentName;//Environment environment;
	private String origin;
	private String description;
	private String detail;
	private Long frequency;
	
	public LogDto(Log log) {
		this.id = log.getId();
		this.createdAt = log.getCreatedAt();
		this.levelName = log.getLevel().getName();
		this.environmentName = log.getEnvironment().getName();
		this.origin = log.getOrigin();
		this.description = log.getDescription();
		this.detail = log.getDetails();
		this.frequency = 0L;
	}
	
	public LogDto() {
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

	public Long getFrequency() {
		return frequency;
	}

	public void setFrequency(Long frequency) {
		this.frequency = frequency;
	}

	public String getDetail() {
		return detail;
	}

	public void setDetail(String detail) {
		this.detail = detail;
	}

}
