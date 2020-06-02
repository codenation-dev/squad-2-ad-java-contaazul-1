package com.javawomen.errorcenter.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javawomen.errorcenter.controller.dto.RoleDto;
import com.javawomen.errorcenter.model.Role;
import com.javawomen.errorcenter.repository.RoleRepository;
import com.javawomen.errorcenter.service.interfaces.RoleServiceInterface;

@Service
public class RoleService implements RoleServiceInterface{

	@Autowired
	RoleRepository roleRepository;

	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	public long count() {
		return roleRepository.count();
	}	
	
	public Optional<Role> findByName(String roleName) {
		return roleRepository.findByRoleName(roleName);
	}
	
	public Role save(Role object) {
		return roleRepository.save(object);
	}
	
	public Optional<Role> findById(Long id) {
		return roleRepository.findById(id);
	}
	
	public List<RoleDto> converter(List<Role> roles) {
		return roles.stream().map(RoleDto::new).collect(Collectors.toList());
	}
	
	public Role converter(String roleName) {
		return new Role(roleName);
	}

}
