package com.javawomen.errorcenter.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javawomen.errorcenter.model.User;


//extends o crudRepository
@Repository
public interface UserRepository extends JpaRepository<User, Long>{	
	//save ok testado
	//delete
	//findAll ok
	//findByEmail ok
	//put nao outr
	
	//é usado em: AuthenticationService
	Optional<User> findByEmail(@Param("email") String email);

	Optional<User> findById(@Param("id")String id);
	
}
