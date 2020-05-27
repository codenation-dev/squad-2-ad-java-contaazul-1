package com.javawomen.errorcenter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
@Table(name = "environment", uniqueConstraints={@UniqueConstraint(columnNames={"name"})})
public class Environment {
	
	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotBlank(message = "{nameEnvironment.not.blank}") 
	@Size(min = 3, max = 100) 
	@Column(name = "name")
	private String name;	
	
	public Environment() {
	}
	
	public Environment(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}	
	
	
	
}
