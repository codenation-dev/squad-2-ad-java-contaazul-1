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

// só tem sentido criar novo role e deleta role se mudar a premissão em security
// como o security é um método interno, para mudar tem que entrar no código e mudar a regre de negocio

@Service
public class RoleService implements RoleServiceInterface{

	@Autowired
	RoleRepository roleRepository;

	public List<Role> findAll() {
		return roleRepository.findAll();
	}

	public long count() {//usado no primeiro acesso para cadastrar roles
		return roleRepository.count();
	}	
	
	//usado para criar os roles no inicio do systema
	public Role converter(String roleName) {
		return new Role(roleName);
	}
	
	public List<RoleDto> converter(List<Role> roles) {
		return roles.stream().map(RoleDto::new).collect(Collectors.toList());
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
}
