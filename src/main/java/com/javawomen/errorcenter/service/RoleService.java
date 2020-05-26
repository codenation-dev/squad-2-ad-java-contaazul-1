package com.javawomen.errorcenter.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.javawomen.errorcenter.controller.dto.RoleDto;
import com.javawomen.errorcenter.controller.form.RoleForm;
import com.javawomen.errorcenter.model.Role;

public interface RoleService {
		
	public List<Role> findAll();

	public Optional<Role> findById(Long id);

	public Role save(Role object);

	public void deleteById(Long id);

	public Optional<Role> findByName(String roleName);
	
	public RoleDto converterToRole(Optional<Role> roleOptional);
	
	public RoleDto converterToRole(Role role);

	public List<RoleDto> converter(List<Role> roles);

	public Role converter(RoleForm form);
}
