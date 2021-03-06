package com.javawomen.errorcenter.service;

import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.javawomen.errorcenter.config.validation.ResourceNotFoundException;
import com.javawomen.errorcenter.controller.dto.LogDto;
import com.javawomen.errorcenter.controller.form.LogForm;
import com.javawomen.errorcenter.data.Archive;
import com.javawomen.errorcenter.model.Environment;
import com.javawomen.errorcenter.model.Level;
import com.javawomen.errorcenter.model.Log;
import com.javawomen.errorcenter.repository.LogRepository;
import com.javawomen.errorcenter.service.interfaces.LogServiceInterface;

@Service
public class LogService implements LogServiceInterface{

	@Autowired
	LogRepository logRepository;

	public Page<Log> findAll(Pageable paginacao) {
		return logRepository.findAll(paginacao);
	}

	public List<Log> findAll() {
		return logRepository.findAll();
	}

	public Page<Log> findByEnvironment(String nameEnvironment, Pageable paginacao) {
		return logRepository.findByEnvironmentName(nameEnvironment.toUpperCase(), paginacao);
	}

	public Optional<Log> findById(Long id) {
		return logRepository.findById(id);
	}

	public List<Log> findByLevel(String nameLevel) {
		List<Log> logs = logRepository.findByLevelName(nameLevel.toUpperCase());								
		if (logs.isEmpty())
			throw new ResourceNotFoundException("Não encontrado log com este nível");
		return logs;
	}

	public List<Log> findByEnvironment(String nameEnvironment) {
		List<Log> logs = logRepository.findByEnvironmentName(nameEnvironment.toUpperCase());
		if (logs.isEmpty())
			throw new ResourceNotFoundException("Não encontrado log com este nível");
		return logs;
	}

	public List<Log> findByDescription(String description) {
		List<Log> logs = logRepository.findByDescription(description);
		if (logs.isEmpty())
			throw new ResourceNotFoundException("Não encontrado log com este nível");
		return logs;
	}

	public List<Log> findByOrigin(String origin) {
		List<Log> logs = logRepository.findByOrigin(origin);
		if (logs.isEmpty())
			throw new ResourceNotFoundException("Não encontrado log com este nível");
		return logs;
	}

	public Log save(Log object) {
		return logRepository.save(object);
	}

	public void deleteById(Long id) {
		logRepository.deleteById(id);
	}

	// --------------------------- FREQUENCY -----------------------------------
	// devolve o numero de vezes que um log aparece no banco
	public Long countByAttribute(Long id) {
		Optional<Log> logOptional = logRepository.findById(id);
		if (!logOptional.isPresent())
			throw new ResourceNotFoundException("ID não encontrado.");
		Log log = logOptional.get();
		return logRepository.countByAllAttribute(log.getLevel().getName(), log.getEnvironment().getName(),
				log.getOrigin(), log.getDescription());
	}

	// ordena logs pela frequencia
	public Map<Long, List<LogDto>> countByAttributeList() {
		List<LogDto> frequencyList = new ArrayList<>();
		List<Log> logs = findAll();
		
		// transforma em DTO
		for (Log log : logs) {
			Long count = countByAttribute(log.getId());
			LogDto dto = converterToLog(log);
			dto.setFrequency(count);
			frequencyList.add(dto);
		}
		// A chave é a frequencia
		Map<Long, List<LogDto>> frequencyMap = frequencyList.stream()
				.collect(Collectors.groupingBy(LogDto::getFrequency));
		return frequencyMap;
	}

	// devolve log de um ambiente ordenado pela frequencia
	public Map<Long, List<LogDto>> countByEnvironmentList(String nameEnvironment) {
		List<LogDto> frequencyList = new ArrayList<>();
		List<Log> logs = logRepository.findByEnvironmentName(nameEnvironment.toUpperCase());
		if(logs.isEmpty()) {
			throw new ResourceNotFoundException("Ambiente não encontrado.");
		}
		// transforma em DTO
		for (Log log : logs) {
			Long count = countByAttribute(log.getId());
			LogDto dto = converterToLog(log);
			dto.setFrequency(count);
			frequencyList.add(dto);
		}
		// A chave é a frequencia
		Map<Long, List<LogDto>> frequencyMap = frequencyList.stream()
				.collect(Collectors.groupingBy(LogDto::getFrequency));

		return frequencyMap;

	}

	
	//------------------------ MÉTODO COM DTO  ----------------------

	public Page<LogDto> converter(Page<Log> logs) {
		return logs.map(LogDto::new);
	}

	public List<LogDto> converterToLog(List<Log> logs) {
		return logs.stream().map(LogDto::new).collect(Collectors.toList());
	}

	public LogDto converterToLog(Optional<Log> logOptional) {
		return new LogDto(logOptional.get());
	}

	public LogDto converterToLog(Log log) {
		return new LogDto(log);
	}
	
	//----------------------- MÉTODO COM FORM  ----------------------
	public Log converter(LevelService levelService, EnvironmentService environmentService, LogForm form) {
		Optional<Level> levelOptional = levelService.findByName(form.getNameLevel().toUpperCase());
		Optional<Environment> environmentOptional = environmentService.findByName(form.getNameEnvironment().toUpperCase());

		if(!levelOptional.isPresent())throw new ResourceNotFoundException("Level não encontrado.");
		if(!environmentOptional.isPresent())throw new ResourceNotFoundException("Ambiente não encontrado.");

		return new Log(levelOptional.get(), environmentOptional.get(), form.getOrigin(), form.getDescription(), form.getDetails());

	}
	
	//---------------------  ARQUIVA LOG EM TXT ---------------------
	public void archiveLog(Long id) throws IOException {
		Optional<Log> logOptional = logRepository.findById(id);
		if (!logOptional.isPresent()) throw new ResourceNotFoundException("ID não encontrado.");
		LogDto dto = converterToLog(logOptional);
		Archive archive = new Archive();
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd_HHmmssSSSS");
		String date = dto.getCreatedAt().format(formatter);
		archive.write(dto, date);
	}
	
	//---------------------  LEITURA DE ARQUIVO ---------------------
	public List<LogDto>readArchives() throws Throwable{
		Archive archive = new Archive();
		return archive.readArchive();
	}
	
}
