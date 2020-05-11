package com.javawomen.errorcenter.controller;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.CacheEvict;
//import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.javawomen.errorcenter.config.validation.ResourceNotFoundException;
import com.javawomen.errorcenter.controller.dto.LogDto;
import com.javawomen.errorcenter.controller.form.LogForm;
import com.javawomen.errorcenter.model.Log;
import com.javawomen.errorcenter.repository.EnvironmentRepository;
import com.javawomen.errorcenter.repository.LevelRepository;
import com.javawomen.errorcenter.repository.LogRepository;
import com.javawomen.errorcenter.service.LogService;

@RestController
@RequestMapping("/logs")
public class LogController {

	@Autowired
	private LogRepository logRepository;
	@Autowired
	private LevelRepository levelRepository;
	@Autowired
	private EnvironmentRepository environmentRepository;
	@Autowired
	private LogService logService;

	// ------------------ GET ALL --------------------------------
	// @CrossOrigin(origins = "http://localhost:8080")
	// @Cacheable(value="getAllLogs")
	// testado ok: traz uma lista de logs cadastrados
	// @GetMapping(value = "/{environment}"), produces="application/json")
	// @RequestMapping("/logs") ASC ou DESC

	// endpoint: http://localhost:8080/logs
	@GetMapping
	public Page<LogDto> getLogs(@RequestParam(required = false) String nameEnvironment,
			@PageableDefault(sort = "createdAt", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
		if (nameEnvironment == null) {
			Page<Log> logs = logRepository.findAll(paginacao);
			return LogDto.converter(logs);
		} else {
			Page<Log> logs = logRepository.findByEnvironmentName(nameEnvironment, paginacao);
			return LogDto.converter(logs);
		}
	}

	// ------------------ GET BY ID --------------------------------
	// Page<LogDto> //@Valid
	// endpoint: http://localhost:8080/logs/Long
	// @CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("/{id}")
	@Transactional
	public LogDto getLogById(@PathVariable Long id) {
		Optional<Log> log = logService.findByIds(id);// .orElseThrow(()-> new ObjectNotFoundException("Nenhum registro
														// encontrado.",Log.class.getName()));
		return LogDto.converterToLog(log);
	}

	// ------------------ GET BY LEVEL--------------------------------
	// Page<LogDto> //@Valid
	// endpoint:http://localhost:8080/logs/level/debug; error; warning

	@GetMapping("/level/{level}") // (params ="levelName") -> (@RequestParam("levelName") String level)
	public ResponseEntity<List<LogDto>> getLogByLevel(@PathVariable String level) {
		List<Log> logs = logService.findByLevel(level);// resumir, colocando dentro do return
		return ResponseEntity.ok(LogDto.converterToLog(logs));
	}

	// ------------------ GET BY ENVIRONMENT --------------------------------
	// Page<LogDto> //@Valid
	// endpoint:http://localhost:8080/logs/environment/producao;development;homologacao
	@GetMapping("/environment/{environment}")
	public ResponseEntity<List<LogDto>> getLogByEnvironment(@PathVariable String environment) {
		List<Log> logs = logService.findByEnvironment(environment);
		return ResponseEntity.ok(LogDto.converterToLog(logs));
	}

	// ------------------ GET BY DESCRIPTION (TITTEL)
	// --------------------------------
	// Page<LogDto> //@Valid
	// endpoint: http://localhost:8080/logs/description/derscricao do log (titulo)
	@GetMapping("/description/{description}")
	public ResponseEntity<List<LogDto>> getLogByDescription(@PathVariable String description) {
		List<Log> logs = logService.findByDescription(description);
		return ResponseEntity.ok(LogDto.converterToLog(logs));
	}

	// ------------------ GET BY ORIGIN --------------------------------
	// Page<LogDto> //@Valid
	// endpoint:http://localhost:8080/logs/origin/derscricao da origem
	@GetMapping("/origin/{origin}")
	public ResponseEntity<List<LogDto>> getLogByOrigin(@PathVariable String origin) {
		List<Log> logs = logService.findByOrigin(origin);
		return ResponseEntity.ok(LogDto.converterToLog(logs));// .ok(T body)
	}

	// ------------------ POST --------------------------------
	// @CacheEvict(value="getAllLogs", allEntries = true)
	@PostMapping
	@Transactional //com void devolve 200, deveria retornar 201//      : cadastra o log no bco
	public ResponseEntity<LogDto> createLog(@RequestBody @Valid LogForm form, UriComponentsBuilder uriBuilder) {		// o post é RequestBody: spring pega no corpo e nao na url eihn!
		
		Log log = form.converter(levelRepository, environmentRepository);
		logRepository.save(log);	
		URI uri = uriBuilder.path("/logs/{id}").buildAndExpand(log.getId()).toUri();
		return ResponseEntity.created(uri).body(new LogDto(log));
	}

	// ------------------ DELETE --------------------------------
	// @CacheEvict(value="getAllLogs", allEntries = true)
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteLog(@PathVariable Long id) {
		Optional<Log> optional = logRepository.findById(id);
		if (optional.isPresent()) {
			logRepository.deleteById(id);
			return ResponseEntity.ok().build();
		}
		return ResponseEntity.notFound().build();
	}



}
