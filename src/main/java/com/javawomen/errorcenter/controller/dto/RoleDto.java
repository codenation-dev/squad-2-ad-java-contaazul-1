package com.javawomen.errorcenter.controller.dto;
 
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

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

	// --------------- static ------------

/*	public static RoleDto converterToRole(Optional<Role> roleOptional) {
		return converterToRole(roleOptional.get());
	}
	
	public static RoleDto converterToRole(Role role) {
		return new RoleDto(role);
	}

	public static List<RoleDto> converter(List<Role> roles) {
		return roles.stream().map(RoleDto::new).collect(Collectors.toList());
	}
*/
}
