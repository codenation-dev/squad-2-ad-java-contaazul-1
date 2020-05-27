package com.javawomen.errorcenter.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javawomen.errorcenter.model.ResetToken;

@Repository
public interface ResetTokenRepository extends JpaRepository<ResetToken, Long>{	
	
	Optional<ResetToken> findByToken(@Param("token") String token);
	
	/*
	Optional<ResetToken> findByEmail(@Param("email") String email);
	
	
	
	@Query
	("select r from ResetToken r where r.email = :email")
	ResetToken findUserByEmail(String email);
	
	@Query
	("select r from ResetToken r where r.token = :token")
	ResetToken findUserByToken(String token);
	*/
}
