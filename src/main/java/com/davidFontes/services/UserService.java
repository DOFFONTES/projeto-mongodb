package com.davidFontes.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davidFontes.domain.User;
import com.davidFontes.dto.UserDTO;
import com.davidFontes.repository.UserRepository;
import com.davidFontes.service.exception.ObjetoNaoEncontradoException;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;

	public List<User> buscaTodos(){
		return repo.findAll();	  
	}
	
	public User buscaPorId(String id) {
		Optional<User> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException("Objeto não encontrado"));	
	}
	
	public User inserir(User obj) {
		return repo.insert(obj);
	}
	
	public void delete(String id) {
		buscaPorId(id);
		repo.deleteById(id);
	}
	
	public User atualiza(User obj) {
		User newObj = buscaPorId(obj.getId());
		atualizaDado(newObj, obj);
		return repo.save(newObj);
	}
	
	private void atualizaDado(User newObj, User obj) {
		newObj.setNome(obj.getNome());
		newObj.setEmail(obj.getEmail());	
	}

	public User userDTOParaUser(UserDTO objDTO) {
		return new User(objDTO.getId(), objDTO.getNome(), objDTO.getEmail());
	}
}
