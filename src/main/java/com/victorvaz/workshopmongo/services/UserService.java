package com.victorvaz.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victorvaz.workshopmongo.domain.User;
import com.victorvaz.workshopmongo.repository.UserRepository;
import com.victorvaz.workshopmongo.services.exception.ObjectNotFoundException;

@Service // Fala para o spring que esse serviço e injetavel em outras classes 
public class UserService { //Serviço acessa o repositorio
	
	@Autowired //Instancia automaticamente um objeto nesse serviço
	private UserRepository repo; // mecanismo de injeção de dependencia do Spring
	
	public List<User> findAll(){ //Esse serviço deve conversar com um Repositorio, no caso UserRepository
		return repo.findAll(); //vai no banco e retorna todos os objetos de usuario
	}
	
	public User findById(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado"));
	}
}
