package com.javawomen.errorcenter.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.javawomen.errorcenter.config.validation.ResourceNotFoundException;
import com.javawomen.errorcenter.controller.dto.LogDto;
import com.javawomen.errorcenter.controller.form.LogForm;
import com.javawomen.errorcenter.model.Environment;
import com.javawomen.errorcenter.model.Level;
import com.javawomen.errorcenter.model.Log;

public interface LogService {
	public List<Log> findAll() ;
	
	public Page<Log> findByEnvironment(String nameEnvironment, Pageable paginacao);

	public Optional<Log> findById(Long id);

	public List<Log> findByLevel(String nameLevel);

	public List<Log> findByEnvironment(String nameEnvironment);

	public List<Log> findByDescription(String description);

	public List<Log> findByOrigin(String origin);
	
	public Log save(Log object);
	
	public void deleteById(Long id);
	
	public Long countByAttribute(Long id);
	
	public Map<Long, List<LogDto>> countByAttributeList();
	
	public int compare(LogDto p1, LogDto p2);
	
	public Map<Long, List<LogDto>> countByEnvironmentList(String nameEnvironment);
	
	public int compare(LogDto p1, LogDto p2);
	
	public Page<LogDto> converter(Page<Log> logs);
	
	public List<LogDto> converterToLog(List<Log> logs);

	public LogDto converterToLog(Optional<Log> logOptional);
	
	public LogDto converterToLog(Log log);
	
	public Log converter(LevelServiceImpl levelService, EnvironmentServiceImpl environmentService, LogForm form);

}
