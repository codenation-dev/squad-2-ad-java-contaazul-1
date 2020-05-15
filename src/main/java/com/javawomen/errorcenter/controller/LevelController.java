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
import com.javawomen.errorcenter.controller.dto.LevelDto;
import com.javawomen.errorcenter.controller.form.LevelForm;
import com.javawomen.errorcenter.model.Level;
import com.javawomen.errorcenter.service.LevelService;

@RestController
@RequestMapping("/levels")
public class LevelController {
	
	@Autowired
	private LevelService levelService;
	
	 
	// ------------------ GET ALL -------------------------------

	@GetMapping
	public List<LevelDto> getAllLevel(){
		List<Level> levels = levelService.findAll();
		return LevelDto.converter(levels);
	}

	// ------------------ GET BY ID --------------------------------

	@GetMapping("/{id}")
	public LevelDto getLevelById(@PathVariable Long id) {	
		Optional<Level> levelOptional = levelService.findById(id);
		if(!levelOptional.isPresent())throw new ResourceNotFoundException("Level não encontrado.");
		return LevelDto.converterToLevel(levelOptional);
	}

	// ------------------ POST --------------------------------

	@PostMapping
	@Transactional
	public ResponseEntity<LevelDto> createLevel(@RequestBody @Valid LevelForm form,
			UriComponentsBuilder uriBuilder) {
		Level level = form.converter();
		levelService.save(level);
		URI uri = uriBuilder.path("/levels/{id}").buildAndExpand(level.getId()).toUri();
		return ResponseEntity.created(uri).body(new LevelDto(level));
	}

	// ------------------ DELETE --------------------------------
	
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteLevel(@PathVariable Long id) {
		Optional<Level> optionalLevel = levelService.findById(id);
		if(!optionalLevel.isPresent())throw new ResourceNotFoundException("ID não encontrado.");
		levelService.deleteById(id);
		return ResponseEntity.ok(LevelDto.converterToLevel(optionalLevel));
	}
}
