package com.javawomen.errorcenter.controller.form;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
 
public class RoleForm {
	
	@NotBlank(message = "{nameRole.not.blank}")
	@Length(min = 3)
    private String roleName;
	
	
	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String name) {
		this.roleName = name;
	}

}
