package com.javawomen.errorcenter.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.Set;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Sort.Direction;

import com.javawomen.errorcenter.config.validation.ResourceNotFoundException;
import com.javawomen.errorcenter.controller.dto.LogDto;
import com.javawomen.errorcenter.model.Environment;
import com.javawomen.errorcenter.model.Log;
import com.javawomen.errorcenter.repository.LogRepository;

/**
 * @author Letícia
 *
 */

@Service
public class LogService {// implements ServiceInterface<Log> {

	@Autowired
	LogRepository logRepository;

	public Page<Log> findAll(Pageable paginacao) {
		return logRepository.findAll(paginacao);
	}

	public List<Log> findAll() {
		return logRepository.findAll();
	}

	public Page<Log> findByEnvironment(String nameEnvironment, Pageable paginacao) {
		return logRepository.findByEnvironmentName(nameEnvironment, paginacao);
	}

	public Optional<Log> findById(Long id) {
		return logRepository.findById(id);
	}

	public List<Log> findByLevel(String nameLevel) {
		return logRepository.findByLevelName(nameLevel);
	}

	public List<Log> findByEnvironment(String nameEnvironment) {
		return logRepository.findByEnvironmentName(nameEnvironment);
	}

	public List<Log> findByDescription(String description) {
		return logRepository.findByDescription(description);
	}

	public List<Log> findByOrigin(String origin) {
		return logRepository.findByOrigin(origin);
	}

	public Log save(Log object) {
		return logRepository.save(object);
	}

	public void deleteById(Long id) {
		logRepository.deleteById(id);
	}

	// receber uma lista retornar uma lista ordenada com frequencia ASC
	// esse seria o endpoint de eventos, para a última tela, o clietne teria que
	// trabalhar com dois endpoints: buscar o log, e buscar a frequencia
	// public long countByAttribute(String levelName, String environmentName, String
	// originName, String descriptionName){
	// public long countByAttribute(String levelName, String environmentName, String
	// originName, String descriptionName) {

	// ---------------- TESTAR ---------------------------------------------

	// pegar um log e devolver o numero de vezes que ele aparece no banco
	public Long countByAttribute(Long id) {
		Optional<Log> logOptional = logRepository.findById(id);
		if (!logOptional.isPresent())
			throw new ResourceNotFoundException("ID não encontrado.");
		Log log = logOptional.get();
		return logRepository.countByAllAttribute(log.getLevel().getName(), log.getEnvironment().getName(),
				log.getOrigin(), log.getDescription());
	}

	// pegar todos os logs e devolver ordenado pela frequencia
	public Map<Long, List<LogDto>> countByAttributeList() {

		List<LogDto> frequencyList = new ArrayList<>();
		List<Log> logs = findAll();

		// transforma em DTO
		for (Log log : logs) {
			Long count = countByAttribute(log.getId());
			LogDto dto = LogDto.converterToLog(log);
			dto.setFrequency(count);
			frequencyList.add(dto);
		}
		// compara a frequency
		class ComparatorDto implements Comparator<LogDto> {
			public int compare(LogDto p1, LogDto p2) {
				return p1.getFrequency() < p2.getFrequency() ? -1 : (p1.getFrequency() > p2.getFrequency() ? +1 : 0);
			}
		}
		// realiza ordenação
		// Comparator<LogDto> crescente = new ComparatorDto();
		// Comparator<LogDto> decrescente = Collections.reverseOrder(crescente);
		// ordena a lista em ordem desc
		// Collections.sort(frequencyList, decrescente);

		// A chave é a frequencia
		Map<Long, List<LogDto>> frequencyMap = frequencyList.stream()
				.collect(Collectors.groupingBy(LogDto::getFrequency));

		// --- inicio ------ ordenação decrescente por chave
		// class MyComparator implements Comparator<Object> { Map map;
		// public MyComparator(Map map) { this.map = map; }
		// public int compare(Object o1, Object o2) {
		// return ((Integer) map.get(o2)).compareTo((Integer) map.get(o1)); } }
		//
		// MyComparator comp = new MyComparator(myMap);
		// Map newMap = new TreeMap(Collections.reverseOrder());
		// newMap.putAll(myMap);
		// --- fim

		return frequencyMap;
	}

	// pegar todos os logs e devolver ordenado pela frequencia
	public Map<Long, List<LogDto>> countByEnvironmentList(String nameEnvironment) {

		List<LogDto> frequencyList = new ArrayList<>();
		List<Log> logs = logRepository.findByEnvironmentName(nameEnvironment);
		
		if(logs.isEmpty()) {
			throw new ResourceNotFoundException("Ambiente não encontrado.");
		}

		// transforma em DTO
		for (Log log : logs) {
			Long count = countByAttribute(log.getId());
			LogDto dto = LogDto.converterToLog(log);
			dto.setFrequency(count);
			frequencyList.add(dto);
		}
		// compara a frequency
		class ComparatorDto implements Comparator<LogDto> {
			public int compare(LogDto p1, LogDto p2) {
				return p1.getFrequency() < p2.getFrequency() ? -1 : (p1.getFrequency() > p2.getFrequency() ? +1 : 0);
			}
		}

		// A chave é a frequencia
		Map<Long, List<LogDto>> frequencyMap = frequencyList.stream()
				.collect(Collectors.groupingBy(LogDto::getFrequency));

		return frequencyMap;

	}

	// falta implementar: -------------------------------------------

	// Page<LogDto> LogDto.converter(Page<Log> logs);

	// List<LogDto> LogDto.converterToLog(List<Log> logs);

	// Log form.converter(LevelRepository levelRepository, EnvironmentRepository
	// environmentRepository);

}
