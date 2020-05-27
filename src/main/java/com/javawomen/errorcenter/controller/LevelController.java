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
import com.javawomen.errorcenter.controller.dto.LevelDto;
import com.javawomen.errorcenter.controller.form.LevelForm;
import com.javawomen.errorcenter.model.Level;
import com.javawomen.errorcenter.service.LevelService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * Nível do Log
 */
@Api(tags = "3. Nível de log - ")
@RestController
@RequestMapping(path = "/levels", produces = MediaType.APPLICATION_JSON_VALUE)
@CrossOrigin(origins = "*")//libera os dominios de acesar a api: http://dominio.com.br
public class LevelController{
	
	@Autowired
	private LevelService levelService;
	
	 
	// -------------------------- GET ALL ---------------------------
	// http://localhost:8080/levels
	@ApiOperation(value = "Retorna uma lista de níveis de log existentes")
	@GetMapping
	public ResponseEntity<List<LevelDto>> getAllLevel(){
		List<Level> levels = levelService.findAll();
		return ResponseEntity.ok(levelService.converter(levels));
	}

	// ------------------------ GET BY ID ---------------------------
	// http://localhost:8080/levels/{id}
	@ApiOperation(value = "Retorna um nível cadastrado")
	@GetMapping("/{id}")
	public ResponseEntity<LevelDto> getLevelById(@PathVariable Long id) {	
		Optional<Level> levelOptional = levelService.findById(id);
		if(!levelOptional.isPresent())
			throw new ResourceNotFoundException("Level não encontrado.");
		return  ResponseEntity.ok(levelService.converterToDto(levelOptional.get()));
	}

	// --------------------------- POST -----------------------------
	// http://localhost:8080/levels/#@RequestBody
	@ApiOperation(value = "Cria um novo nível")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<LevelDto> createLevel(@RequestBody @Valid LevelForm levelName,
			UriComponentsBuilder uriBuilder) {
		Level level = levelService.converter(levelName);//testar
		levelService.save(level);
		URI uri = uriBuilder.path("/levels/{id}").buildAndExpand(level.getId()).toUri();
		return ResponseEntity.created(uri).body(new LevelDto(level));
	}

	// -------------------------- DELETE ----------------------------
	// http://localhost:8080/levels/{id}
	@ApiOperation(value = "Exclui um nível")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteLevel(@PathVariable Long id) {
		Optional<Level> optionalLevel = levelService.findById(id);
		if(!optionalLevel.isPresent())throw new ResourceNotFoundException("Level não encontrado.");
		levelService.deleteById(id);
		return ResponseEntity.ok(levelService.converterToDto(optionalLevel.get()));
	}
}
