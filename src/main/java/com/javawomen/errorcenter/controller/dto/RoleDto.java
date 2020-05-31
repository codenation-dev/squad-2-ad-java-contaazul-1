package com.javawomen.errorcenter.controller.dto;
 

import com.javawomen.errorcenter.model.Role;

public class RoleDto {

	private Long id;
	private String roleName;

	public RoleDto(Role role) {
		this.id = role.getId();
		this.roleName = role.getRoleName();
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

}
