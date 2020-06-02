package com.javawomen.errorcenter.service.interfaces;

import java.util.List;
import java.util.Optional;

import com.javawomen.errorcenter.model.Role;

public interface RoleServiceInterface {

	List<Role> findAll();

	long count();
	
	Optional<Role> findByName(String roleName);
	
	Role converter(String roleName);
		
}
