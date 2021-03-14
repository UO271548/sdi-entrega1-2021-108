package com.uniovi.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.uniovi.entities.User;

public interface UsersRepository extends CrudRepository<User, Long> {

	User findByEmail(String dni);
	
	@Query("SELECT r FROM User r WHERE (LOWER(r.role) LIKE LOWER('ROLE_ESTANDAR'))")
	List<User> findUserNotAdmin();

}
