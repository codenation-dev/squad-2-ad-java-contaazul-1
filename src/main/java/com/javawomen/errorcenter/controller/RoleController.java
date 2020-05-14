package com.javawomen.errorcenter.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
import com.javawomen.errorcenter.controller.form.RoleForm;
import com.javawomen.errorcenter.model.Role;
import com.javawomen.errorcenter.service.RoleService;

@RestController
@RequestMapping("/roles")
public class RoleController {

	@Autowired
	private RoleService roleService;

	// ------------------ GET ALL -------------------------------

	@GetMapping
	public List<RoleDto> getAllRole() {
		List<Role> Roles = roleService.findAll();
		return RoleDto.converter(Roles);
	}

	// ------------------ GET BY ID --------------------------------

	@GetMapping("/{id}")
	public RoleDto getRoleById(@PathVariable Long id) {
		Optional<Role> RoleOptional = roleService.findById(id);
		if (!RoleOptional.isPresent())
			throw new ResourceNotFoundException("Role não encontrado.");
		return RoleDto.converterToRole(RoleOptional);
	}

	// ------------------ POST --------------------------------

	@PostMapping
	@Transactional
	public ResponseEntity<RoleDto> createRole(@RequestBody @Valid RoleForm form, UriComponentsBuilder uriBuilder) {
		Role Role = form.converter();
		roleService.save(Role);
		URI uri = uriBuilder.path("/roles/{id}").buildAndExpand(Role.getId()).toUri();
		return ResponseEntity.created(uri).body(new RoleDto(Role));
	}

	// ------------------ DELETE --------------------------------

	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteRole(@PathVariable Long id) {
		Optional<Role> optionalRole = roleService.findById(id);
		if (!optionalRole.isPresent())
			throw new ResourceNotFoundException("ID não encontrado.");
		roleService.deleteById(id);
		return ResponseEntity.ok(RoleDto.converterToRole(optionalRole));
	}
}
