package com.javawomen.errorcenter.model;

//import javax.persistence.Column;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name = "level")
public class Level {
	
	@Id //javax.persistence.Id;
	@GeneratedValue(strategy = GenerationType.IDENTITY) //@GeneratedValue(strategy = GenerationType.SEQUENCE)//uso sequence por conta do bco ser postgresql
	private Long id;	//@Column(name="level_id")
	
	//@NotNull
	//@Size (max =  50)
	//@Column(name="levelName")
	private String name;
	
	
	
	public Level(Long id, String name) {
		this.id = id;
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
