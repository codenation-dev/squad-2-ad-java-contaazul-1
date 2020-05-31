package com.javawomen.errorcenter.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javawomen.errorcenter.model.Log;
 
@Repository
public interface LogRepository extends JpaRepository<Log, Long> {	
	
	Page<Log> findByEnvironmentName(String nameEnvironment, Pageable paginacao);
	
	List<Log> findByLevelName(String level); 

	List<Log> findByEnvironmentName(String environmentName);

	List<Log> findByOrigin(String origin);

	List<Log> findByDescription(String description);
	
	// Devolve o numero de vezes que um log aparece no banco
	@Query
	("select count(p) from Log p "
			+ "join p.level le "
			+ "join p.environment en "
			+ "where le.name = :levelName and en.name =:environmentName "
			+ "and p.origin = :originName and p.description = :descriptionName")
	Long countByAllAttribute(@Param("levelName")String levelName, @Param("environmentName")String environmentName, @Param("originName")String originName, @Param("descriptionName")String descriptionName);
	
}
