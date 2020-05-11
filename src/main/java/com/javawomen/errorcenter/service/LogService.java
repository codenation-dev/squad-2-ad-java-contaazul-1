package com.javawomen.errorcenter.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.javawomen.errorcenter.model.Log;
import com.javawomen.errorcenter.repository.LogRepository;

/**
 * @author Letícia
 *
 */

@Service
public class LogService{// implements ServiceInterface<Log> {
	@Autowired
	LogRepository logRepository;

	//@Override
	public Page<Log> findAll(Pageable paginacao) {
		return logRepository.findAll(paginacao);
	}

	//@Override
	public Optional<Log> findByIds(Long id) {
		return logRepository.findById(id);
	}

	//@Override
	public Log save(Log object) {
		return logRepository.save(object);
	}

	//@Override
	public void deleteById(Long id) {
		logRepository.deleteById(id);
	}

	// OK
	public List<Log> findByLevel(String nameLevel) {
		return logRepository.findByLevelName(nameLevel);
	}

	// OK
	public List<Log> findByEnvironment(String nameEnvironment) {
		return logRepository.findByEnvironmentName(nameEnvironment); //Page<Log> // Pageable paginacao);
	}
	
	// OK
	public List<Log> findByDescription(String description) {
		return logRepository.findByDescription(description);
	}

	//OK
	public List<Log> findByOrigin(String origin) {				
		return logRepository.findByOrigin(origin);
	}

	
	// falta implementar: -------------------------------------------


	// Page<LogDto> LogDto.converter(Page<Log> logs);

	// List<LogDto> LogDto.converterToLog(List<Log> logs);

	// Log form.converter(LevelRepository levelRepository, EnvironmentRepository
	// environmentRepository);

}
