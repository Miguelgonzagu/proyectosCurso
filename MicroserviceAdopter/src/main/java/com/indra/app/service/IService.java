package com.indra.app.service;

import java.util.List;

import com.indra.app.entities.Adopter;


public interface IService {
	
	//registrar adoptante
	boolean insert(Adopter a);
	//consultar todos los adoptantes
	List<Adopter> findAll();
	//buscar adoptante por id
	Adopter findById(long id);
	//buscar adoptante por email
	Adopter findByEmail(String email);
	//buscar adoptante por apellido
	Adopter findBylastname(String lastname);
	//actualizar información de un adoptante
	boolean update(Adopter a);
	//eliminar adoptante por id
	boolean deleteById(long id);
}
