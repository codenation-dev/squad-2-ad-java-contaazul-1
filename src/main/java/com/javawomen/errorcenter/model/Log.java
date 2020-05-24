package com.javawomen.errorcenter.model;

//import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;

 
@Entity@Table(name = "log")
public class Log {

	 //javax.persistence.Id;
	@Id @Column(name="log_id") @GeneratedValue(strategy = GenerationType.SEQUENCE) //@GeneratedValue(strategy = GenerationType.SEQUENCE)//uso sequence por conta do bco ser postgresql
	private Long id;	
	
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")   
    @Column(name = "created_at")  
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @ManyToOne
	private Level level;
    
    @ManyToOne 
	private Environment environment;    
    
    @Length(min = 5, max = 50)
    private String origin;
    
    @Length(min = 10, max = 100)
    private String description;
    
    @Length(min = 10, max = 100)
    private String Details;

    /*
     * datetime: representa uma data como no calendário e a hora como encontrado no relógio.
		timestamp: representa um ponto específico na linha do tempo e leva em consideração o fuso horário em questão (UTC). 
     * 
     */

    public String getDetails() {
		return Details;
	}


	public void setDetails(String details) {
		Details = details;
	}


	//construtor para jpa
	public Log() {}

	
	public Log(Level level, Environment environment, String origin, String description) {
		this.level = level;
		this.environment = environment;
		this.origin = origin;
		this.description = description;
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