package com.javawomen.errorcenter.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Collection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Size;
//import javax.persistence.UniqueConstraint;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;

//<<UserDetails>> essa classe é a q tem detalhes do user
@Entity              
@Table(name = "person", uniqueConstraints={@UniqueConstraint(columnNames={"email"})}) //não são chave-primária, no entanto, precisam possuir valores únicos
public class User implements UserDetails{ 
	
	
	private static final long serialVersionUID = 1L;
	
	
	@Id //vem do javax.persistence.Id;
	@GeneratedValue(strategy = GenerationType.SEQUENCE) //@GeneratedValue(strategy = GenerationType.SEQUENCE)//uso sequence por conta do bco ser postgresql
	@Column(name="user_id") //O tamanho default das colunas é 255.
	private Long id;	
		
    private String name;
	
    @Email //testar
    @Column(name = "email") //@Column é opcional, possuindo valores default https://www.devmedia.com.br/mapeamento-hibernate-configurando-tabelas-e-colunas/29526
    private String email;

    private String password;
    
  
    @NotNull
    @CreatedDate //testar
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")    
    @Column(name = "created_at") //@Column(columnDefinition = "timestamp default now()")
    private LocalDateTime createdAt = LocalDateTime.now();
    
    @ManyToMany(fetch = FetchType.EAGER)		  //qnd carregar o user, ja carrego a lista de perfil dele
    private List<Role> roles = new ArrayList<>(); //pefil, já inicio para não ficar null
    
    
 
    //construtor vazio para a JPA
	public User() {}

	
	public User(String name, String email, String password) {
		this.name = name;
		this.email = email;
		this.password = password;
	}


	//getters e setters para UserDto
	
	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	//public String getPassword() {
	//	return password;
	//}

	public String getEmail() {
		return email;
	}

	public LocalDateTime getCreatedAt() {
		return createdAt;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public void setCreatedAt(LocalDateTime createdAt) {
		this.createdAt = createdAt;
	}

	//-------------------------  USER DETAILS -----------------------------
	
	//GrantedAuthority: para o spring secutrity, além da classe user, precisamos de uma classe que tem o perfil do user
	@Override 
	public Collection<? extends GrantedAuthority> getAuthorities() { //Coleção herda de GrandetAt...
		return this.roles;
	}

	@Override
	public String getPassword() {
		return this.password;
	} 
	
	@Override
	public String getUsername() {
		return this.email;
	}


	@Override
	public boolean isAccountNonExpired() {
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}


	@Override
	public boolean isEnabled() {
		return true;
	}



	

}