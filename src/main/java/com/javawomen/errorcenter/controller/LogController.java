package com.javawomen.errorcenter.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.javawomen.errorcenter.controller.dto.LogDto;
import com.javawomen.errorcenter.controller.form.LogForm;
import com.javawomen.errorcenter.model.Log;
import com.javawomen.errorcenter.repository.EnvironmentRepository;
import com.javawomen.errorcenter.repository.LevelRepository;
import com.javawomen.errorcenter.repository.LogRepository;


@RestController
@RequestMapping("/logs")
public class LogController {

	/*
	 * @GetMapping(value="/{codigo}", produces="application/json")
	 * public @ResponseBody Evento evento(@PathVariable(value="codigo") long codigo)
	 */

	@Autowired
	private LogRepository logRepository;
	@Autowired
	private LevelRepository levelRepository;
	@Autowired
	private EnvironmentRepository environmentRepository;
	
	//------------------   GET ALL   --------------------------------
	//@Cacheable(value="getAllLogs")
	//http://localhost:8080/logs                   ok: traz uma lista de logs cadastrados
	@GetMapping//(value = "/{environment}") // , produces="application/json")//@RequestMapping("/logs") ASC ou DESC 
	public Page<LogDto> getAll(@RequestParam(required = false)String nameEnvironment,@PageableDefault(sort = "createdAt", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {		
		if (nameEnvironment == null) {
			Page<Log> logs = logRepository.findAll(paginacao);
		return LogDto.converter(logs);		
		}else {
			Page<Log> logs = logRepository.findByEnvironmentName(nameEnvironment, paginacao);
			return LogDto.converter(logs);
		}
	}
	
	//------------------   GET BY LEVEL   --------------------------------

	//http://localhost:8080/logs/debug             ok: traz o log cadastrado no email
	@GetMapping(value = "/{level}") // , produces="application/json")//@RequestMapping("/logs")
	public List<LogDto> getLevel(@PathVariable(value = "level") String level) {	
		List<Log> logs = logRepository.findByLevelName(level);
		return LogDto.converterToLog(logs);		
	}
	
	//------------------   POST   --------------------------------
	//@CacheEvict(value="getAllLogs", allEntries = true)
	@PostMapping
	@Transactional //com void devolve 200, deveria retornar 201//      : cadastra o log no bco
	public ResponseEntity<LogDto> createLog(@RequestBody @Valid LogForm form, UriComponentsBuilder uriBuilder) {		// o post é RequestBody: spring pega no corpo e nao na url eihn!
		Log log = form.converter(levelRepository, environmentRepository);
		logRepository.save(log);	
		URI uri = uriBuilder.path("/logs/{id}").buildAndExpand(log.getId()).toUri();
		return ResponseEntity.created(uri).body(new LogDto(log));
	}

	//------------------   DELETE   --------------------------------
	//@CacheEvict(value="getAllLogs", allEntries = true)
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> remover(@PathVariable Long id) {
		Optional<Log> optional = logRepository.findById(id);
		if (optional.isPresent()) {
			logRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		
		return ResponseEntity.notFound().build();
	}
	

}
