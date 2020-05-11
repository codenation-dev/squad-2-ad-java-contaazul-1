package com.javawomen.errorcenter.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javawomen.errorcenter.model.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
	 
	//Optional<Log> findById(Long id);
	
	
	Page<Log> findByEnvironmentName(String nameEnvironment, Pageable paginacao);
	
	List<Log> findByLevelName(String level); 

	List<Log> findByEnvironmentName(String environmentName);

	List<Log> findByOrigin(String origin);

	List<Log> findByDescription(String description);
	
}
