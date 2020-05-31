package com.javawomen.errorcenter.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.javawomen.errorcenter.controller.dto.RoleDto;
import com.javawomen.errorcenter.model.Role;
import com.javawomen.errorcenter.service.RoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Perfil de Usuário
 */
@Api(tags = "6. Perfil de Usuário - ")
@RestController
@RequestMapping(path = "/roles", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class RoleController {

	@Autowired
	private RoleService roleService;
	
	// -------------------------- GET ALL ---------------------------
	// http://localhost:8080/roles
	@ApiOperation(value = "Retorna uma lista de perfis de usuário existentes")
	@GetMapping
	public List<RoleDto> getAllRole() {
		List<Role> Roles = roleService.findAll();
		return roleService.converter(Roles);
	}
	
}
