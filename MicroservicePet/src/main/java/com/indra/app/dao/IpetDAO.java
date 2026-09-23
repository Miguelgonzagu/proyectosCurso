package com.indra.app.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.indra.app.entities.Pet;
import java.util.List;


public interface IpetDAO extends CrudRepository<Pet, Long> {
	
	@Query("SELECT u FROM Pet u WHERE u.specie= :specie")
	Optional<List<Pet>> findBySpecie(@Param("specie") String specie);
	
}
