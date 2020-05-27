package com.javawomen.errorcenter.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.javawomen.errorcenter.config.validation.ResourceNotFoundException;
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
@CrossOrigin(origins = "*")//libera os dominios de acesar a api: http://dominio.com.br
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

	/*
	// ------------------------ GET BY ID ---------------------------
	// http://localhost:8080/roles/{id}
	@ApiOperation(value = "Retorna um perfil cadastrado")
	@GetMapping("/{id}")
	public RoleDto getRoleById(@PathVariable Long id) {
		Optional<Role> RoleOptional = roleService.findById(id);
		if (!RoleOptional.isPresent())
			throw new ResourceNotFoundException("Perfil não encontrado.");
		return roleService.converterToRole(RoleOptional);
	}

	//isso tem que deletar antes do projeto final
	// --------------------------- POST -----------------------------
	// http://localhost:8080/roles/#@RequestBody
	@ApiOperation(value = "Cria um novo perfil")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<RoleDto> createRole(@RequestBody @Valid RoleForm form, 
			UriComponentsBuilder uriBuilder) {
		Role Role = roleService.converter(form);
		roleService.save(Role);
		URI uri = uriBuilder.path("/roles/{id}").buildAndExpand(Role.getId()).toUri();
		return ResponseEntity.created(uri).body(new RoleDto(Role));
	}
	
	//isso tem que deletar antes do projeto final
	// -------------------------- DELETE ----------------------------
	// http://localhost:8080/roles/{id}
	@ApiOperation(value = "Exclui um perfil")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteRole(@PathVariable Long id) {
		Optional<Role> optionalRole = roleService.findById(id);
		if (!optionalRole.isPresent())
			throw new ResourceNotFoundException("Perfil não encontrado.");
		roleService.deleteById(id);
		return ResponseEntity.ok(roleService.converterToRole(optionalRole));
	}
	*/
	
}
