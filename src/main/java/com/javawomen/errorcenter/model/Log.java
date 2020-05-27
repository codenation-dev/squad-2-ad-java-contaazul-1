package com.javawomen.errorcenter.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

 
@Entity
@Table(name = "log")
public class Log {

	@Id 
	@Column(name="log_id") 
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;	
	
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")   
    @Column(name = "created_at")  
    private LocalDateTime createdAt;
    
    @ManyToOne
	private Level level;
    
    @ManyToOne 
	private Environment environment;    
    
    @NotBlank(message = "{origin.not.blank}")
    @Length(min = 5, max = 50)
    private String origin;
    
    @NotBlank(message = "{description.not.blank}")
    @Length(min = 10, max = 100)
    private String description;
    
    @NotBlank(message = "{details.not.blank}")
    @Length(min = 10, max = 255)
    private String details;

    /*
     * datetime: representa uma data como no calendário e a hora como encontrado no relógio.
		timestamp: representa um ponto específico na linha do tempo e leva em consideração o fuso horário em questão (UTC). 
     * 
     */

	public Log() {}

	public Log(Level level, Environment environment, String origin, String description, String details) {
		this.level = level;
		this.environment = environment;
		this.origin = origin;
		this.description = description;
		this.createdAt = LocalDateTime.now();
		this.details = details;
	}

	public Long getId() {
		return id;
	}

	public String getOrigin() {
		return origin;
	}

	public String getDescription() {
		return description;
	}
	
	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public Environment getEnvironment() {
		return environment;
	}

	public Level getLevel() {
		return level;
	}

    public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}
	
	public void setId(Long id) {
		this.id = id;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	public void setEnvironment(Environment environment) {
		this.environment = environment;
	}

	public void setLevel(Level level) {
		this.level = level;
	}



    
    
}