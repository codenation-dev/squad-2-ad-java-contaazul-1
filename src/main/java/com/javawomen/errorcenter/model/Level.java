package com.javawomen.errorcenter.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotBlank;


@Entity 
@Table(name = "level", uniqueConstraints={@UniqueConstraint(columnNames={"name"})}) //não são chave-primária, no entanto, precisam possuir valores únicos
public class Level {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	 
	@NotBlank(message = "{nameLevel.not.blank}") 
	@Size(min = 3, max = 100)
	@Column(name="name")
	private String name;
	

	public Level(String name) {
		this.name = name;
	}
	
	@Deprecated
	public Level(){
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
