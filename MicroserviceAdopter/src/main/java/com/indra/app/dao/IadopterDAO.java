package com.indra.app.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.indra.app.entities.Adopter;


public interface IadopterDAO extends CrudRepository<Adopter, Long> {
	
	@Query("SELECT u FROM Adopter u WHERE u.email= :email")
	Optional<Adopter> findByEmail(@Param("email") String email);
	
	@Query("SELECT u FROM Adopter u WHERE u.lastname= :lastname")
	Optional<Adopter> findByLastName(@Param("lastname") String lastname);
	
	

}
