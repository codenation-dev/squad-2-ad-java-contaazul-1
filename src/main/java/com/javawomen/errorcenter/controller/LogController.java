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

import com.javawomen.errorcenter.config.validation.DataInvalid;
import com.javawomen.errorcenter.config.validation.ResourceNotFoundException;
import com.javawomen.errorcenter.controller.dto.LogDto;
import com.javawomen.errorcenter.controller.form.LogForm;
import com.javawomen.errorcenter.model.Log;
import com.javawomen.errorcenter.service.EnvironmentService;
import com.javawomen.errorcenter.service.LevelService;
import com.javawomen.errorcenter.service.LogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import springfox.documentation.annotations.ApiIgnore;

/**
 * Log
 */
@Api(tags = "2. Log de Erros - ")
@RestController
@RequestMapping(path = "/logs")
@CrossOrigin(origins = "*")
public class LogController {

	@Autowired
	private LogService logService;

	@Autowired
	private EnvironmentService environmentService;

	@Autowired
	private LevelService levelService;

	// -------------------------- GET ALL ---------------------------
	// http://localhost:8080/logs
	@ApiOperation(value = "Retorna uma lista de logs existentes", notes = "Retorna os logs cadastrados. Para ordenar digite um campo válido: description, environment, level, origin, id, createdAt")
	@GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
	@Cacheable("listOfLog") // @Cacheable do spring
	@ApiImplicitParams({
			@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Página à ser carregada", defaultValue = "0"),
			@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Quantidade de registros", defaultValue = "10"),
			@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Ordenação dos registros") })
	public Page<LogDto> getLogs(@RequestParam(required = false) String nameEnvironment,
			@PageableDefault(sort = "createdAt", direction = Direction.ASC, page = 0, size = 10) @ApiIgnore Pageable paginacao) {
		if (nameEnvironment == null) {
			Page<Log> logs = logService.findAll(paginacao);
			return logService.converter(logs);
		} else {
			try {
				Page<Log> logs = logService.findByEnvironment(nameEnvironment, paginacao);
				return logService.converter(logs);
			} catch (Exception e) {
				throw new DataInvalid("Verifique os campos digitados");
			}
		}

	}

	// ------------------------ GET BY ID ---------------------------
	// http://localhost:8080/logs/{id}
	@ApiOperation(value = "Retorna um log cadastrado", notes = "Digite o id do log que deseja consultar")
	@GetMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<LogDto> getLogById(@PathVariable Long id) {
		Optional<Log> logOptional = logService.findById(id);
		if (!logOptional.isPresent())
			throw new ResourceNotFoundException("Log não existe.");
		return ResponseEntity.ok(logService.converterToLog(logOptional));
	}

	// ----------------------- GET BY LEVEL -------------------------
	// http://localhost:8080/logs/level/{level}
	@ApiOperation(value = "Retorna uma lista de logs de um determinado nível", notes = "Digite o nome do nível do log que deseja consultar")
	@GetMapping(path = "/level/{level}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LogDto>> getLogByLevel(@PathVariable String level) {
		List<Log> logs = logService.findByLevel(level);
		return ResponseEntity.ok(logService.converterToLog(logs));
	}

	// --------------------- GET BY ENVIRONMENT ---------------------
	// http://localhost:8080/logs/environment/{environment}
	@ApiOperation(value = "Retorna uma lista de logs de um determinado ambiente", notes = "Digite o nome do ambiente do log que deseja consultar")
	@GetMapping(path = "/environment/{environment}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LogDto>> getLogByEnvironment(@PathVariable String environment) {
		List<Log> logs = logService.findByEnvironment(environment);
		return ResponseEntity.ok(logService.converterToLog(logs));
	}

	// ----------------- GET BY DESCRIPTION (TITTEL)-----------------
	// http://localhost:8080/logs/description/{description}
	@ApiOperation(value = "Retorna uma lista de logs de uma determinada descrição", notes = "Digite o titulo do log que deseja consultar")
	@GetMapping(path = "/description/{description}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LogDto>> getLogByDescription(@PathVariable String description) {
		List<Log> logs = logService.findByDescription(description);
		return ResponseEntity.ok(logService.converterToLog(logs));
	}

	// ------------------------ GET BY ORIGIN -----------------------
	// http://localhost:8080/logs/origin/{origin}
	@ApiOperation(value = "Retorna uma lista de logs de um determinada origem", notes = "Digite a origem do log que deseja consultar")
	@GetMapping(path = "/origin/{origin}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<LogDto>> getLogByOrigin(@PathVariable String origin) {
		List<Log> logs = logService.findByOrigin(origin);
		return ResponseEntity.ok(logService.converterToLog(logs));
	}

	// -------------------- GET ONE BY FREQUENCY ---------------------
	// http://localhost:8080/logs/frequency/{id}
	@ApiOperation(value = "Retorna o número de logs que existe à partir dos parâmetros de um log", notes = "Digite o id do log que deseja consultar sua quantidade no sistema")
	@GetMapping("/frequency/{id}")
	public ResponseEntity<Long> countLog(@PathVariable Long id) {
		return ResponseEntity.ok(logService.countByAttribute(id));
	}

	// -------------------- GET ALL BY FREQUENCY ---------------------
	// devolver ordenado por frequency: o getAll e getByEnvironment
	// http://localhost:8080/logs/frequency?nameEnvironment=producao
	@ApiOperation(value = "Retorna uma coleção com a frequência e uma lista de logs correspondentes", notes = "Retorno = quantidade[lista de logs com essa quantidade], exemplo, 1[log1, log2, log3]")
	@GetMapping("/frequency")
	public ResponseEntity<Map<Long, List<LogDto>>> getLogsFrequency(
			@RequestParam(required = false) String nameEnvironment) {
		if (nameEnvironment == null) {
			return ResponseEntity.ok(logService.countByAttributeList());
		} else {
			return ResponseEntity.ok(logService.countByEnvironmentList(nameEnvironment));
		}
	}

	// --------------------------- POST -----------------------------
	// http://localhost:8080/logs/#@RequestBody
	@ApiOperation(value = "Cria um novo log", notes = "Para criar um novo log digite suas características, os campos level e environment válidos são os que estão cadastrados previamente")
	@PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<LogDto> createLog(@RequestBody @Valid LogForm cadastro, UriComponentsBuilder uriBuilder) {
		Log logObj = logService.converter(levelService, environmentService, cadastro);
		logService.save(logObj);
		URI uri = uriBuilder.path("/logs/{id}").buildAndExpand(logObj.getId()).toUri();
		return ResponseEntity.created(uri).body(new LogDto(logObj));
	}

	// -------------------------- DELETE ----------------------------
	// http://localhost:8080/logs/{id}
	@ApiOperation(value = "Exclui um log", notes = "Para deletar um log digite seu id")
	@DeleteMapping(path = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@Transactional
	public ResponseEntity<?> deleteLog(@PathVariable Long id) {
		Optional<Log> logOptional = logService.findById(id);
		if (!logOptional.isPresent())
			throw new ResourceNotFoundException("Log não encontrado.");
		logService.deleteById(id);
		return ResponseEntity.ok(logService.converterToLog(logOptional));
	} 

	// ------------------------ ARCHIVE ----------------------------
	// http://localhost:8080/logs/{id}
	@ApiOperation(value = "Arquiva um log", notes = "Para arquivar um log digite seu id")
	@PostMapping("/{id}")
	@Transactional
	public ResponseEntity<?> archiveLog(@PathVariable Long id) throws IOException {
		logService.archiveLog(id);
		logService.deleteById(id);
		return ResponseEntity.ok("Log arquivado com sucesso.");
	}

	// ----------------------- READ ARCHIVE -------------------------
	// http://localhost:8080/logs/archive
	@ApiOperation(value = "Retorna os logs arquivados", notes = "Retorna uma lista com os logs que estao no arquivo .txt")
	@GetMapping("/archive")
	public ResponseEntity<List<LogDto>> getLogsArchived() throws Throwable {
		return ResponseEntity.ok(logService.readArchives());
	}

}
