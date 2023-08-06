package com.victorvaz.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.victorvaz.workshopmongo.domain.User;
import com.victorvaz.workshopmongo.dto.UserDTO;
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
	
	public User insert(User obj) {
		return repo.insert(obj);
	}
	
	public void delete (String id) {
		
		findById(id);
		repo.deleteById(id);
	}
	
	public User update (User obj) {
		User newObj = findById(obj.getId());
		updateData(newObj, obj); //responsavel por copiar os novos dados do obj para o newObj
		return repo.save(newObj);
	}
	
	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());
	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}
}
