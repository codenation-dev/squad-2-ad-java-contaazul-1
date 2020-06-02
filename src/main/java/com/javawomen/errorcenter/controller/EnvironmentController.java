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
import com.javawomen.errorcenter.controller.dto.EnvironmentDto;
import com.javawomen.errorcenter.controller.form.EnvironmentForm;
import com.javawomen.errorcenter.model.Environment;
import com.javawomen.errorcenter.service.EnvironmentService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Ambiente
 */
@Api(tags = "4. Ambiente de Log - ")
@RestController
@RequestMapping(path = "/environments", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")
public class EnvironmentController {

	@Autowired
	private EnvironmentService environmentService;
 
	// -------------------------- GET ALL ---------------------------
	// http://localhost:8080/environments
	@ApiOperation(value = "Retorna uma lista de ambientes de log existentes", notes = "Retorna todos os ambientes cadastrados no sistema")
	@GetMapping
	public List<EnvironmentDto> getAllEnvironment() {
		List<Environment> environments = environmentService.findAll();
		if (environments.isEmpty())
			throw new ResourceNotFoundException("Não existe nenhum ambiente cadastrado");
		return environmentService.converter(environments);
	}

	// ------------------------ GET BY ID ---------------------------
	// http://localhost:8080/environments/{id}	
	@ApiOperation(value = "Retorna um ambiente cadastrado", notes = "Digite o ID de um ambiente válido")
	@GetMapping("/{id}")
	public EnvironmentDto getEnvironmentById(@PathVariable Long id) {
		Optional<Environment> environmentOptional = environmentService.findById(id);
		if(!environmentOptional.isPresent())
			throw new ResourceNotFoundException("Ambiente não encontrado.");
		return environmentService.converterToDto(environmentOptional.get());
	}

	// --------------------------- POST -----------------------------
	// http://localhost:8080/environments/#@RequestBody
	@ApiOperation(value = "Cria um novo ambiente", notes = "Digite o nome do ambiente que deseja cadastrar")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<EnvironmentDto> createEnvironment(@RequestBody @Valid EnvironmentForm name,
			UriComponentsBuilder uriBuilder) {
		Environment environment = environmentService.converter(name);
		environmentService.save(environment);
		URI uri = uriBuilder.path("/environments/{id}").buildAndExpand(environment.getId()).toUri();
		return ResponseEntity.created(uri).body(new EnvironmentDto(environment));
	}

	// -------------------------- DELETE ----------------------------
	// http://localhost:8080/environments/{id}
	@ApiOperation(value = "Exclui um Ambiente", notes = "Digite o ID do ambiente que deseja excluir")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteEnvironment(@PathVariable Long id) {
		Optional<Environment> environmentOptional = environmentService.findById(id);
		if(!environmentOptional.isPresent()) throw new ResourceNotFoundException("Ambiente não encontrado.");
		environmentService.deleteById(id);		
		return ResponseEntity.ok(environmentService.converterToDto(environmentOptional.get()));
	}
}
