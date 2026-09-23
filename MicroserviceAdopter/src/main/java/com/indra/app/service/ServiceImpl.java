package com.indra.app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.indra.app.dao.IadopterDAO;
import com.indra.app.entities.Adopter;
import com.indra.app.exception.AdopterNotFoundException;


@Service
public class ServiceImpl implements IService {
	
	//dependency
		private IadopterDAO dao;
		private Adopter a;
		
		//dependency injection
		public ServiceImpl(IadopterDAO dao) {
			this.dao=dao;
		}

	@Override
	public boolean insert(Adopter a) {
		if(a.getId()==0) {
			
			return dao.save(a)!=null;
		}
		return false;
	}

	@Override
	public List<Adopter> findAll() {
		return Optional.of(dao.findAll())
				.map(t->(List<Adopter>)t)
				.filter(t->!t.isEmpty())
				.orElseThrow(()->new AdopterNotFoundException("empty result"));
	}

	@Override
	public Adopter findById(long id) {
		return dao.findById(id)
				.orElseThrow(()->new AdopterNotFoundException("adopter doesn't exists"));
	}

	@Override
	public Adopter findByEmail(String email) {
		return dao.findByEmail(email)
				.orElseThrow(()->new AdopterNotFoundException("email doesn't exists"));
	}

	@Override
	public  Adopter findBylastname(String lastname) {
		return dao.findByLastName(lastname)
				.orElseThrow(()->new AdopterNotFoundException("lastname doesn't exists"));
	}

	@Override
	public boolean update(Adopter a) {
		if (dao.existsById(a.getId())) {
			return dao.save(a)!=null;
		}

		throw new AdopterNotFoundException("adopter "+a.getId()+ "doesn't exists");
	}

	@Override
	public boolean deleteById(long id) {
		if(dao.existsById(id)) {
			dao.deleteById(id);
			return true;
		}
		throw new AdopterNotFoundException("adopter doesn't exists");
	}

}
