package com.javawomen.errorcenter.model;

//import java.sql.Timestamp;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
//import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
//import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
@Table(name = "log")
public class Log {

	 //javax.persistence.Id;
	@Id @Column(name="log_id") @GeneratedValue(strategy = GenerationType.IDENTITY) //@GeneratedValue(strategy = GenerationType.SEQUENCE)//uso sequence por conta do bco ser postgresql
	private Long id;	
	
    //@Column(columnDefinition = "timestamp default now()"
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")   
    @Column(name = "created_at")  
    private LocalDateTime createdAt = LocalDateTime.now();
    //private Timestamp createdAt;   
    
    //@JoinColumn(name="level")
    //@JoinColumn(name="level_id")
    @ManyToOne
	private Level level;
    
    //@JoinColumn(name="environment")
    //@JoinColumn(name="environment_id")
    @ManyToOne
	private Environment environment;    
    
    private String origin;
    private String description;


    /*
     * datetime: representa uma data como no calendário e a hora como encontrado no relógio.
		timestamp: representa um ponto específico na linha do tempo e leva em consideração o fuso horário em questão (UTC). 
     * 
     */


    //construtor para jpa, no curso nao tinha
	public Log() {}

	
	public Log(Level level2, Environment environment2, String origin2, String description2) {
		this.level = level2;
		this.environment = environment2;
		this.origin = origin2;
		this.description = description2;
	}


	//getter 
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

	//setter
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