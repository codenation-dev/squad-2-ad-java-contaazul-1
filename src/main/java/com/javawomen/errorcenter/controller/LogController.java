package com.javawomen.errorcenter.controller;

import java.io.IOException;
import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.MediaType;
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

import com.javawomen.errorcenter.config.validation.ResourceNotFoundException;
import com.javawomen.errorcenter.controller.dto.LogDto;
import com.javawomen.errorcenter.controller.form.LogForm;
import com.javawomen.errorcenter.model.Log;
import com.javawomen.errorcenter.service.EnvironmentService;
import com.javawomen.errorcenter.service.LevelService;
import com.javawomen.errorcenter.service.LogService;

import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import springfox.documentation.annotations.ApiIgnore;
//import org.springframework.cache.annotation.CacheEvict;


@RestController
@RequestMapping(path = "/logs", produces = MediaType.APPLICATION_JSON_VALUE)
public class LogController {
 
	@Autowired
	private LogService logService;
	@Autowired
	private EnvironmentService environmentService;
	@Autowired
	private LevelService levelService;

	// -------------------------- GET ALL ---------------------------
	// http://localhost:8080/logs
	@ApiOperation(value = "Retorna uma lista de logs existentes")
	@GetMapping
	@Cacheable("listOfLog")//@Cacheable do spring
	@ApiImplicitParams({
		@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", 
				value = "Página à ser carregada", defaultValue = "0"), 
		@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", 
				value = "Quantidade de registros", defaultValue = "10"), 
		@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", 
				paramType = "query", value = "Ordenação dos registros")
	})
	public Page<LogDto> getLogs(@RequestParam(required = false) String nameEnvironment,
			@PageableDefault(sort = "createdAt", direction = Direction.ASC, page = 0, size = 10) 
			@ApiIgnore Pageable paginacao) {
		if (nameEnvironment == null) {
			Page<Log> logs = logService.findAll(paginacao);
			return logService.converter(logs);
		} else {
			Page<Log> logs = logService.findByEnvironment(nameEnvironment, paginacao);
			return logService.converter(logs);
		}
	}

	
	//este estah funcionando
	// ------------------ GET ALL --------------------------------
	// @CrossOrigin(origins = "http://localhost:8080")
	// @Cacheable(value="getAllLogs")
	// testado ok: traz uma lista de logs cadastrados
	// @GetMapping(value = "/{environment}"), produces="application/json")
	// @RequestMapping("/logs") ASC ou DESC

	// endpoint: http://localhost:8080/logs
	//@GetMapping
	//public Page<LogDto> getLogs(@RequestParam(required = false) String nameEnvironment,
	//		@PageableDefault(sort = "createdAt", direction = Direction.ASC, page = 0, size = 10) Pageable paginacao) {
	//	if (nameEnvironment == null) {
	//		Page<Log> logs = logService.findAll(paginacao);
	//		return LogDto.converter(logs);
	//	} else {
	//		Page<Log> logs = logService.findByEnvironment(nameEnvironment, paginacao);
	//		return LogDto.converter(logs);
	//	}
	//}
	
	
	
	// ------------------------ GET BY ID ---------------------------
	// http://localhost:8080/logs/{id}
	@ApiOperation(value = "Retorna um log cadastrado")
	@GetMapping("/{id}")
	@Transactional
	public LogDto getLogById(@PathVariable Long id) {
		Optional<Log> logOptional = logService.findById(id);								
		if (!logOptional.isPresent())
			throw new ResourceNotFoundException("Log não exite.");
		return logService.converterToLog(logOptional);
	}

	// ----------------------- GET BY LEVEL -------------------------
	// http://localhost:8080/logs/level/{level}
	@ApiOperation(value = "Retorna uma lista de logs de um determinado nível")
	@GetMapping("/level/{level}")
	public ResponseEntity<List<LogDto>> getLogByLevel(@PathVariable String level) {
		List<Log> logs = logService.findByLevel(level);
		return ResponseEntity.ok(logService.converterToLog(logs));
	}

	// --------------------- GET BY ENVIRONMENT ---------------------
	// http://localhost:8080/logs/environment/{environment}
	@ApiOperation(value = "Retorna uma lista de logs de um determinado ambiente")
	@GetMapping("/environment/{environment}")
	public ResponseEntity<List<LogDto>> getLogByEnvironment(@PathVariable String environment) {
		List<Log> logs = logService.findByEnvironment(environment);
		return ResponseEntity.ok(logService.converterToLog(logs));
	}

	// ----------------- GET BY DESCRIPTION (TITTEL)-----------------
	// http://localhost:8080/logs/description/{description}
	@ApiOperation(value = "Retorna uma lista de logs de uma determinada descrição")
	@GetMapping("/description/{description}")
	public ResponseEntity<List<LogDto>> getLogByDescription(@PathVariable String description) {
		List<Log> logs = logService.findByDescription(description);
		return ResponseEntity.ok(logService.converterToLog(logs));
	}

	// ------------------------ GET BY ORIGIN -----------------------
	// http://localhost:8080/logs/origin/{origin}
	@ApiOperation(value = "Retorna uma lista de logs de um determinada origem")
	@GetMapping("/origin/{origin}")
	public ResponseEntity<List<LogDto>> getLogByOrigin(@PathVariable String origin) {
		List<Log> logs = logService.findByOrigin(origin);
		return ResponseEntity.ok(logService.converterToLog(logs));
	}
	
	//-------------------- GET ONE BY FREQUENCY ---------------------   TESTAR ResponseEntity
	// pegar um log e devolver o numero de vezes que ele aparece no banco
	// esse metodo preenche o requisito de frequencia para a ultima tela, ele
	// corresponde apenas ao Log que está sendo apresentado
	// http://localhost:8080/logs/frequency/{id}
	@ApiOperation(value = "Retorna o número de logs que existe à partir dos parâmetros de um log")
	@GetMapping("/frequency/{id}")
	public ResponseEntity<Long> countLog(@PathVariable Long id) {
		return ResponseEntity.ok(logService.countByAttribute(id));
	}

	//-------------------- GET ALL BY FREQUENCY ---------------------
	// devolver ordenado por frequency: o getAll e getByEnvironment 
	//http://localhost:8080/logs/frequency?nameEnvironment=producao
	@ApiOperation(value = "Retorna uma coleção com a frequência e uma lista de logs correspondentes")
	@GetMapping("/frequency")
	public ResponseEntity<Map<Long, List<LogDto>>> getLogsFrequency(
			@RequestParam(required = false)String nameEnvironment) {		
		if (nameEnvironment == null) {
			return ResponseEntity.ok(logService.countByAttributeList());
		} else {
			return ResponseEntity.ok(logService.countByEnvironmentList(nameEnvironment));
		}
	}
	
	// --------------------------- POST -----------------------------
	// @CacheEvict(value="getAllLogs", allEntries = true)
	// http://localhost:8080/logs/#@RequestBody
	@ApiOperation(value = "Cria um novo log")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<LogDto> createLog(@RequestBody @Valid LogForm form, 
			UriComponentsBuilder uriBuilder) {
		Log log = logService.converter(levelService, environmentService, form);
		logService.save(log);
		URI uri = uriBuilder.path("/logs/{id}").buildAndExpand(log.getId()).toUri();
		return ResponseEntity.created(uri).body(new LogDto(log));
	}

	// -------------------------- DELETE ----------------------------
	// http://localhost:8080/logs/{id}
	// @CacheEvict(value="getAllLogs", allEntries = true)
	@ApiOperation(value = "Exclui um log")
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteLog(@PathVariable Long id){
		Optional<Log> logOptional = logService.findById(id);
		if (!logOptional.isPresent())
			throw new ResourceNotFoundException("Log não encontrado.");
		logService.deleteById(id);
		return ResponseEntity.ok(logService.converterToLog(logOptional));
	}

	// --------------------=---- ARCHIVE ----------------------------
	// http://localhost:8080/logs/{id}
	@ApiOperation(value = "Arquiva um log")
	@PostMapping("/{id}")
	@Transactional
	public ResponseEntity<?> archiveLog(@PathVariable Long id) 
			throws IOException{
		logService.archiveLog(id);
		logService.deleteById(id);
		return ResponseEntity.ok("Log arquivado com sucesso.");
	}
	

}
