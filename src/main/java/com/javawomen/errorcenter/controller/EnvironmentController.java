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
import com.javawomen.errorcenter.controller.dto.EnvironmentDto;
import com.javawomen.errorcenter.controller.form.EnvironmentForm;
import com.javawomen.errorcenter.model.Environment;
import com.javawomen.errorcenter.service.EnvironmentService;
//import com.javawomen.errorcenter.service.EnvironmentService;

@RestController
@RequestMapping("/environments")
public class EnvironmentController {

	@Autowired
	private EnvironmentService environmentService;

	// ------------------ GET ALL -------------------------------

	@GetMapping
	public List<EnvironmentDto> getAllEnvironment() {
		List<Environment> environments = environmentService.findAll();
		return EnvironmentDto.converter(environments);
	}

	// ------------------ GET BY ID --------------------------------
	
	@GetMapping("/{id}")
	public EnvironmentDto getEnvironmentById(@PathVariable Long id) {// throws NotFoundException {
		Optional<Environment> environmentOptional = environmentService.findById(id);
		if(!environmentOptional.isPresent())throw new ResourceNotFoundException("Ambiente não encontrado.");
		return EnvironmentDto.converterToEnvironment(environmentOptional);
	}

	// ------------------ POST --------------------------------

	@PostMapping
	@Transactional
	public ResponseEntity<EnvironmentDto> createEnvironment(@RequestBody @Valid EnvironmentForm form,
			UriComponentsBuilder uriBuilder) {
		Environment environment = form.converter();
		environmentService.save(environment);
		URI uri = uriBuilder.path("/environments/{id}").buildAndExpand(environment.getId()).toUri();
		return ResponseEntity.created(uri).body(new EnvironmentDto(environment));
	}

	// ------------------ DELETE --------------------------------
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteEnvironment(@PathVariable Long id) {
		Optional<Environment> environmentOptional = environmentService.findById(id);
		if(!environmentOptional.isPresent())throw new ResourceNotFoundException("ID não encontrado.");
		environmentService.deleteById(id);		
		return ResponseEntity.ok(EnvironmentDto.converterToEnvironment(environmentOptional));
	}
}
