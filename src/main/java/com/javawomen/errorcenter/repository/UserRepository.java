package com.javawomen.errorcenter.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.javawomen.errorcenter.model.Role;
import com.javawomen.errorcenter.model.User;


//JpaRepository extends o crudRepository
@Repository
public interface UserRepository extends JpaRepository<User, Long>{	
	
	//Não mudar é usado em: AuthenticationService
	Optional<User> findByEmail(@Param("email") String email);
	
	@Query
	("select u from User u where u.email = :emailUser")//esse atributo é da classe do user e nao da tabela
	User findUserByEmail(String emailUser);

	@Query
	("select roles from User u where u.id = :id")//esse atributo é da classe do user e nao da tabela
	List<Role> findRolesByUser(Long id);
}
