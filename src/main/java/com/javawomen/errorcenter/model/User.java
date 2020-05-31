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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity              
@Table(name = "TBuser", uniqueConstraints={@UniqueConstraint(columnNames={"email"})})
public class User implements UserDetails{ 
	
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="user_id")
	private Long id;	
		
	@NotBlank(message = "{name.not.blank}")
	@Size(min = 3, max = 100)
    private String name;
	
	@NotBlank(message = "{email.not.blank}")
    @Email(message = "{email.not.valid}")
    @Column(name = "email")
    private String email;

	@NotBlank(message = "{password.not.blank}")
	@Size(min = 8, max = 255)		
    private String password;
      
    @NotNull
    @CreatedDate
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @ManyToMany(fetch = FetchType.EAGER)		  
    private List<Role> roles = new ArrayList<>(); 
 
    @Deprecated
	public User(){
    }
	
	public User(String name, String password, String email, Role role) {
		this.name = name;
		this.email = email;
		this.password = password;
		this.setRoles(role);
		this.createdAt = LocalDateTime.now();
	}
	
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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(Role role) {
		this.roles.add(role);
	}
	
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
	//-------------------------  USER DETAILS -----------------------------
	
	@Override 
	public Collection<? extends GrantedAuthority> getAuthorities(){
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