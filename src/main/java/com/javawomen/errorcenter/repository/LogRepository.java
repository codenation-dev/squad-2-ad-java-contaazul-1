package com.javawomen.errorcenter.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javawomen.errorcenter.model.Log;

@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
	 
	List<Log> findByLevelName(@Param("level") String level); //esse query é automático

	Page<Log> findByEnvironmentName(String nameEnvironment, Pageable paginacao);

	List<Log> findByEnvironmentName(String environmentName);

	List<Log> findByOrigin(String origin);

	List<Log> findByDescription(String description);
	
}
