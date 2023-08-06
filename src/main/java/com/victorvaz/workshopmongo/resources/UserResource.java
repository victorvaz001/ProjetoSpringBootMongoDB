package com.victorvaz.workshopmongo.resources;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.victorvaz.workshopmongo.domain.User;
import com.victorvaz.workshopmongo.dto.UserDTO;
import com.victorvaz.workshopmongo.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource { //Controlador REST acessa o serviço
	
	@Autowired //Injetando o serviço
	private UserService service; 
	
	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<UserDTO>> findAll(){ //ResponseEntity -> Retorna possiveis cabecalhos, erros		
		List<User> list = service.findAll();
		
		//Converte cada objeto da lista original para um DTO
		List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listDto); //Instancia o objeto com corpo de resposta, no caso o 'list'
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<UserDTO> findById(@PathVariable String id){ //ResponseEntity -> Retorna possiveis cabecalhos, erros				
		/*@PathVariable -> para dizer que o id do argumento seja casado com o id da url*/
		
		User obj = service.findById(id);
		
	
		return ResponseEntity.ok().body(new UserDTO(obj)); //retorna o (obj) de User convertido para UserDTO
	}
	
	
}
