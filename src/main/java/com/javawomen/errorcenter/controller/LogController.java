package com.javawomen.errorcenter.controller;

import java.net.URI;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import javax.transaction.Transactional;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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

import com.javawomen.errorcenter.config.validation.ResourceNotFoundException;
import com.javawomen.errorcenter.controller.dto.LogDto;
import com.javawomen.errorcenter.controller.form.LogForm;
import com.javawomen.errorcenter.model.Log;
import com.javawomen.errorcenter.service.EnvironmentService;
import com.javawomen.errorcenter.service.LevelService;
import com.javawomen.errorcenter.service.LogService;

//import springfox.documentation.annotations.ApiIgnore;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/logs")
public class LogController {
 
	@Autowired
	private LogService logService;
	@Autowired
	private EnvironmentService environmentService;
	@Autowired
	private LevelService levelService;

	// ------------------ GET ALL TESTADO OK --------------------------------
	// endpoint: http://localhost:8080/logs
	@GetMapping
	@Cacheable("listOfLog")//importei o do spring, verififcar se nao tem q ser o javax
	@ApiImplicitParams({
		@ApiImplicitParam(name = "page", dataType = "integer", paramType = "query", value = "Pagina a ser carregada", defaultValue = "0"), 
		@ApiImplicitParam(name = "size", dataType = "integer", paramType = "query", value = "Quantidade de registros", defaultValue = "10"), 
		@ApiImplicitParam(name = "sort", allowMultiple = true, dataType = "string", paramType = "query", value = "Ordenação dos registros")
	})
	public Page<LogDto> getLogs(@RequestParam(required = false) String nameEnvironment,
			@PageableDefault(sort = "createdAt"
			, direction = Direction.ASC, page = 0, size = 10) @ApiIgnore Pageable paginacao) {
		if (nameEnvironment == null) {
			Page<Log> logs = logService.findAll(paginacao);
			return LogDto.converter(logs);
		} else {
			Page<Log> logs = logService.findByEnvironment(nameEnvironment, paginacao);
			return LogDto.converter(logs);
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
	
	
	
	// ------------------ GET BY ID --------------------------------
	// Page<LogDto> //@Valid
	// endpoint: http://localhost:8080/logs/Long
	// @CrossOrigin(origins = "http://localhost:8080")
	@GetMapping("/{id}")
	@Transactional
	public LogDto getLogById(@PathVariable Long id) {
		Optional<Log> logOptional = logService.findById(id);// .orElseThrow(()-> new ObjectNotFoundException("Nenhum
															// registro
		// encontrado.",Log.class.getName()));
		if (!logOptional.isPresent())
			throw new ResourceNotFoundException("ID não encontrado.");

		return LogDto.converterToLog(logOptional);
	}

	// ------------------ GET BY LEVEL --------------------------------------
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

	// ------------------ GET BY DESCRIPTION (TITTEL)-----------------------------
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
	
	//-----------------   TESTADO ok   --------------------------------
	
	// 1 -  pegar um log e devolver o numero de vezes que ele aparece no banco

	// esse metodo preenche o requisito de frequencia para a ultima tela, ele
	// corresponde apenas ao Log que está sendo apresentado
	@GetMapping("/frequency/{id}")
	Long countLog(Long id) {
		return logService.countByAttribute(id);
	}

	// 2  - devolver ordenado por frequency: o getAll e getByEnvironment 
	//http://localhost:8080/logs/frequency?nameEnvironment=producao
	@GetMapping("/frequency")
	public ResponseEntity<Map<Long, List<LogDto>>> getLogsFrequency(@RequestParam(required = false) String nameEnvironment) {		
		if (nameEnvironment == null) {
			return ResponseEntity.ok(logService.countByAttributeList());
		} else {
			return ResponseEntity.ok(logService.countByEnvironmentList(nameEnvironment));
		}
	}
	
	
	// ------------------ POST --------------------------------
	// @CacheEvict(value="getAllLogs", allEntries = true)
	@PostMapping
	@Transactional // com void devolve 200, deveria retornar 201// : cadastra o log no bco
	public ResponseEntity<LogDto> createLog(@RequestBody @Valid LogForm form, UriComponentsBuilder uriBuilder) {
		Log log = form.converter(levelService, environmentService);
		logService.save(log);
		URI uri = uriBuilder.path("/logs/{id}").buildAndExpand(log.getId()).toUri();
		return ResponseEntity.created(uri).body(new LogDto(log));
	}

	// ------------------ DELETE --------------------------------
	// @CacheEvict(value="getAllLogs", allEntries = true)
	@DeleteMapping("/{id}")
	@Transactional
	public ResponseEntity<?> deleteLog(@PathVariable Long id) {
		Optional<Log> logOptional = logService.findById(id);
		if (!logOptional.isPresent())
			throw new ResourceNotFoundException("ID não encontrado.");
		logService.deleteById(id);
		return ResponseEntity.ok(LogDto.converterToLog(logOptional));
	}

}
