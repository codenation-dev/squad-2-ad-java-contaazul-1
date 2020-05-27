package com.javawomen.errorcenter.service.interfaces;

import java.util.List;

import com.javawomen.errorcenter.controller.dto.RoleDto;
import com.javawomen.errorcenter.model.Role;

public interface RoleServiceInterface {

	List<Role> findAll();

	long count();
	
	Role converter(String roleName);
	
	List<RoleDto> converter(List<Role> roles);
	
}
