package com.javawomen.errorcenter.controller.form;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.javawomen.errorcenter.model.Role;
 
public class RoleForm {
	
	@NotNull(message = "{nameLevel.not.null}")
	@NotEmpty(message = "{nameLevel.not.empty}")
	@NotBlank(message = "{nameLevel.not.blank}")
    private String roleName;
	
	
	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String name) {
		this.roleName = name;
	}
	
//	public Role converter() {
//		return new Role(roleName);
//	}

}
