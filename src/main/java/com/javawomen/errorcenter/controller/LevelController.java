package com.javawomen.errorcenter.controller;

import java.net.URI;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister.NotFoundException;
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

import com.javawomen.errorcenter.model.Level;
import com.javawomen.errorcenter.repository.LevelRepository;

@RestController
@RequestMapping("/levels")
public class LevelController {

	@Autowired
	private LevelRepository levelRepository;
	
	
	// ------------------ GET ALL -------------------------------

	@GetMapping
	public Page<Level> getAllLevel(
			@PageableDefault(sort = "id", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {

		Page<Level> levels = levelRepository.findAll(paginacao);
		return levels;
	}

	// ------------------ GET BY ID --------------------------------
	// ver a questao dessa notfoundexception
	@GetMapping("/{id}")
	public Level getLevelById(@PathVariable Long id) throws NotFoundException {
		return levelRepository.findById(id).orElseThrow(() -> new NotFoundException());

	}

	// ------------------ POST --------------------------------
	// tentei receber string name ao invés do obj, tentei usar o dto e oform, de
	// qualquer maneira estava ignorando a unique constraint
	@PostMapping
	@Transactional
	public ResponseEntity<Level> createLevel(@RequestBody @Valid Level level,
			UriComponentsBuilder uriBuilder) {
		levelRepository.save(level);
		URI uri = uriBuilder.path("/environments/{id}").buildAndExpand(level.getId()).toUri();
		return ResponseEntity.created(uri).body(level);
	}

	// ------------------ DELETE --------------------------------
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteLevel(@PathVariable Long id) {
		Optional<Level> optionalLevel = levelRepository.findById(id);

		if (optionalLevel.isPresent()) {
			levelRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}

		return ResponseEntity.notFound().build();
	}
}
