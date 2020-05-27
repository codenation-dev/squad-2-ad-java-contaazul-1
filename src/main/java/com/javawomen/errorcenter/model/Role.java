package com.javawomen.errorcenter.model;
 
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.springframework.security.core.GrantedAuthority;

@Entity
@Table(name = "role", uniqueConstraints={@UniqueConstraint(columnNames={"roleName"})}) //não são chave-primária, no entanto, precisam possuir valores únicos)
public class Role implements GrantedAuthority { 
	
	private static final long serialVersionUID = 1L;

	@Id 
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	private Long id;
	
	@NotBlank
	@Size(min = 3, max = 100)
	private String roleName; //Tem que seguir este padrão: ROLE_USER, ROLE_ADMIN

	
	public Role() {
	}
	
	public Role(String roleName) {
		this.roleName = roleName;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	@Override
	public String getAuthority() {
		return this.roleName;
	}
}
