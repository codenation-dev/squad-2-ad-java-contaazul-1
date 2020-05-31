package com.javawomen.errorcenter.service.interfaces;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.javawomen.errorcenter.controller.dto.LogDto;
import com.javawomen.errorcenter.controller.form.LogForm;
import com.javawomen.errorcenter.model.Log;
import com.javawomen.errorcenter.service.EnvironmentService;
import com.javawomen.errorcenter.service.LevelService;

public interface LogServiceInterface {
	
	Page<Log> findAll(Pageable paginacao);
   
	List<Log> findAll();

    Page<Log> findByEnvironment(String nameEnvironment, Pageable paginacao);

	Optional<Log> findById(Long id);

	List<Log> findByLevel(String nameLevel);

	List<Log> findByEnvironment(String nameEnvironment);

	List<Log> findByDescription(String description);

	List<Log> findByOrigin(String origin);

	Log save(Log object);
	
	void deleteById(Long id);
	
	Long countByAttribute(Long id);
	
	Map<Long, List<LogDto>> countByAttributeList();
	
	Map<Long, List<LogDto>> countByEnvironmentList(String nameEnvironment);
	
	Page<LogDto> converter(Page<Log> logs);
	
	List<LogDto> converterToLog(List<Log> logs);

	LogDto converterToLog(Optional<Log> logOptional);

	LogDto converterToLog(Log log);
	
	Log converter(LevelService levelService, EnvironmentService environmentService, LogForm form);
	
	void archiveLog(Long id) throws IOException;
	
	//List<LogDto> readArchiveLog(String archiveName) throws Throwable;
	
}
