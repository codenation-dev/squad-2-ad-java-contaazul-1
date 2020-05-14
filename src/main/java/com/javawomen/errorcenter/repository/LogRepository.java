package com.javawomen.errorcenter.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
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
	
	
	
	
	//--------------     TESTAR     ------------------------
	//pegar um log e devolver o numero de vezes que ele aparece no banco
	@Query
	("select count(p) from Log p "
			+ "join p.level le "
			+ "join p.environment en "
			+ "where le.name = :levelName and en.name =:environmentName "
			+ "and p.origin = :originName and p.description = :descriptionName")
	Long countByAllAttribute(String levelName, String environmentName, String originName, String descriptionName);
	
	//pegar todos e trazer ordenado por frequencia
	//List<Log> findAll();
	
	//pegar todos de um ambiente e trazer ordenado por frequencia
	//List<Log> findByEnvironmentName(String environmentName);
	

	
	
	
	

	
	
	
}
