package com.javawomen.errorcenter.service;

import java.util.Optional;

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
public class LogService implements ServiceInterface<Log>{

	LogRepository logRepository;

	@Override
	public Page<Log> findAll(Pageable paginacao) {
		return logRepository.findAll(paginacao);
	}

	@Override
	public Optional<Log> findById(Long id) {
		return logRepository.findById(id);
	}

	@Override
	public Log save(Log object) {
		return logRepository.save(object);
	}

	@Override
	public void deleteById(Long id) {
		logRepository.deleteById(id);
	}
	
	//falta implementar:
	
	//Page<Log> logRepository.findByEnvironmentName(String nameEnvironment, Pageable paginacao);

	//Page<LogDto> LogDto.converter(Page<Log> logs);
	
	//List<Log> logRepository.findByLevelName(String level);
	
	//List<LogDto> LogDto.converterToLog(List<Log> logs);
	
	//Log form.converter(LevelRepository levelRepository, EnvironmentRepository environmentRepository);

}
