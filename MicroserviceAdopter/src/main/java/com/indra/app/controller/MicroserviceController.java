package com.indra.app.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.indra.app.entities.Adopter;
import com.indra.app.exception.AdopterNotFoundException;
import com.indra.app.service.IService;

@RestController //json
@RequestMapping("/adopters")// http://ip:port/adopters
public class MicroserviceController {
	
	private IService service;
	private static final Logger LOGGER = LoggerFactory.getLogger(MicroserviceController.class);
	
public MicroserviceController(IService service) {
		
		this.service =service;
	}

/*Mapping methods
 * insert -> post
 * query -> get
 * update -> put
 * partial update -> patch
 * delete -> delete
 */

/*insert*/
@PostMapping
public ResponseEntity<String> insert(@RequestBody Adopter a){
	
	try {
		if(service.insert(a)) {
			return new ResponseEntity<>("OK", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<>("bad request", HttpStatus.BAD_REQUEST);
		}
		
	}catch (Exception ex) {
		LOGGER.error("insert {}",ex.getMessage());
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}


@GetMapping
public ResponseEntity<List<Adopter>> findAll(){
	
	try {
		return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
		
	}catch(AdopterNotFoundException ex) {
		LOGGER.warn("FINDALL {}", ex.getMessage());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}catch(Exception ex) {
		LOGGER.error("FINDALL {}", ex.getMessage());
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}


@GetMapping("/id")//http://ip:port/adopters/id?id=2
public ResponseEntity<Adopter> findById(@RequestParam("id") long id){
	
	try {
		return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
		
	}catch(AdopterNotFoundException ex) {
		LOGGER.warn("FINDBYID {}", ex.getMessage());
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}catch(Exception ex) {
		LOGGER.error("FINDBYID {}", ex.getMessage());
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}

@GetMapping("/email")
public ResponseEntity<Adopter> findByEmail(@RequestParam("email") String email){
	
	try {
		return new ResponseEntity<>(service.findByEmail(email), HttpStatus.OK);
		
	}catch(AdopterNotFoundException ex) {
		LOGGER.warn("FINDBYEMAIL {}", ex.getMessage());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}catch(Exception ex) {
		LOGGER.error("FINDBYEMAIL {}", ex.getMessage());
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}

@GetMapping("/lastname")
public ResponseEntity<Adopter> findByLastName(@RequestParam("lastname") String lastname){
	
	try {
		return new ResponseEntity<>(service.findBylastname(lastname), HttpStatus.OK);
		
	}catch(AdopterNotFoundException ex) {
		LOGGER.warn("FINDBYLASTNAME {}", ex.getMessage());
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}catch(Exception ex) {
		LOGGER.error("FINDBYLASTNAME {}", ex.getMessage());
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}

@PutMapping
public ResponseEntity<Boolean> updateById(@RequestBody Adopter a){
	
	try {
		return new ResponseEntity<>(service.update(a), HttpStatus.OK);
		
	}catch(AdopterNotFoundException ex) {
		LOGGER.warn("UPTDATEBYID {}", ex.getMessage());
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}catch(Exception ex) {
		LOGGER.error("UPTDATEBYID {}", ex.getMessage());
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}



@DeleteMapping
public ResponseEntity<Boolean> deleteById(@RequestParam("id") long id){
	
	try {
		return new ResponseEntity<>(service.deleteById(id), HttpStatus.OK);
		
	}catch(AdopterNotFoundException ex) {
		LOGGER.warn("DELETEBYID {}", ex.getMessage());
		return new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}catch(Exception ex) {
		LOGGER.error("DELETEBYID {}", ex.getMessage());
		return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
}


}
