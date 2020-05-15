package com.javawomen.errorcenter.model;

import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import javax.validation.constraints.NotBlank;


@Entity 
@Table(name = "level", uniqueConstraints={@UniqueConstraint(columnNames={"name"})}) //não são chave-primária, no entanto, precisam possuir valores únicos
public class Level {
	
	@Id //javax.persistence.Id;
	@GeneratedValue(strategy = GenerationType.SEQUENCE) //@GeneratedValue(strategy = GenerationType.SEQUENCE)//uso sequence por conta do bco ser postgresql
	private Long id;	//@Column(name="level_id")
	
	@NotNull @NotBlank @Size(min = 3, max = 15) 
	@Column(name="name")	
	private String name;
	
	
	
	public Level(String name) {
		this.name = name;
	}

	public Level() {
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

	//-----  Relacionamentos
	//verificar se isso estah correto
	//@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	//private List<Log> logList;
	

}
