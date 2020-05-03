package com.javawomen.errorcenter.model;

import javax.persistence.Column;
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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity      
@Table(name = "environment", uniqueConstraints={@UniqueConstraint(columnNames={"name"})})//, name = "name")}) //não são chave-primária, no entanto, precisam possuir valores únicos
public class Environment {
	
	 
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY) //@GeneratedValue(strategy = GenerationType.SEQUENCE)//uso sequence por conta do bco ser postgresql
	private Long id;	//se colocar agora tem que mudar muitas outras coisas @Column(name="environment_id")
	
	@NotNull @NotBlank @Size (min =  3) //-> nos outros é validado no formController
	@Column(name = "name")//, unique = true)
	private String name;	
	
	
	public Environment() {
	}

	//public Environment(String name) {
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
