package com.javawomen.errorcenter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.javawomen.errorcenter.model.Role;
import com.javawomen.errorcenter.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	RoleRepository roleRepository;
	
	
	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	public Optional<Role> findById(Long id) {
		return roleRepository.findById(id);
	}

	public Role save(Role object) {
		return roleRepository.save(object);
	}

	public void deleteById(Long id) {
		roleRepository.deleteById(id);		
	}

	public Optional<Role> findByName(String roleName) {
		return roleRepository.findByRoleName(roleName);
	}
}
