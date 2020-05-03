package com.javawomen.errorcenter.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
//import javassist.NotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.javawomen.errorcenter.model.Environment;
import com.javawomen.errorcenter.repository.EnvironmentRepository;
//import com.javawomen.errorcenter.service.EnvironmentService;

@RestController
@RequestMapping("/environments")
public class EnvironmentController {

	@Autowired
	private EnvironmentRepository environmentRepository;
	
	//@Autowired
	//private EnvironmentService environmentService;
	
	//------------------   GET ALL   -------------------------------
	
	@GetMapping
	public Page<Environment> getAllEnvironment(
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		
		Page<Environment> environments = environmentRepository.findAll(paginacao);
		return environments;
	}
	
	// ------------------ GET BY ID --------------------------------
	// ver a questao dessa notfoundexception
	@GetMapping("/{id}") 
	public Environment getEnvironmentById(@PathVariable Long id) throws NotFoundException {
		//Optional<Environment> environment = environmentRepository.findById(id);
		//return environment.get();		

			return environmentRepository.findById(id).orElseThrow(()->new NotFoundException());

	}
	
	//------------------   POST   --------------------------------
	
	//tentei receber string name ao invés do obj, tentei usar o dto e oform, de qualquer maneira estava ignorando a unique constraint
	@PostMapping
	@Transactional 
	public ResponseEntity<Environment> createEnvironment(@RequestBody @Valid Environment  environment, UriComponentsBuilder uriBuilder) {		
		environmentRepository.save(environment);	
		URI uri = uriBuilder.path("/environments/{id}").buildAndExpand(environment.getId()).toUri();
		return ResponseEntity.created(uri).body(environment);
	}
	
	//------------------   DELETE   --------------------------------
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteEnvironment(@PathVariable Long id) {
		Optional<Environment> optional = environmentRepository.findById(id);
		
		if (optional.isPresent()) {
			environmentRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
}
