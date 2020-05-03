package com.javawomen.errorcenter.model;

//import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
/*
 * o Spring Data usa os métodos EntityListeners e de retorno de chamada do JPA 
 * para atualizar automaticamente as propriedades 
 * CreatedBy, CreatedDate, LastModifiedBy, LastModifiedDate.
 * serve para criar logs.
 * nao estou usando o : //@EntityListeners(AuditingEntityListener.class)
 */


@Entity      
@Table(name = "environment")
public class Environment {
	
	@Id //javax.persistence.Id;
	@GeneratedValue(strategy = GenerationType.IDENTITY) //@GeneratedValue(strategy = GenerationType.SEQUENCE)//uso sequence por conta do bco ser postgresql
	private Long id;	//@Column(name="environment_id")
	
	//@NotNull @Size (max =  100) -> é validado no formController
	private String name;	
	
	
	
	
	public Environment() {
	}

	//public Environment(Long id, String name) {
	//	this.id = id;
	//	this.name = name;
	//}

	
	
	
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
