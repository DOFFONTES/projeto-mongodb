package com.davidFontes.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.davidFontes.domain.Post;
import com.davidFontes.domain.User;
import com.davidFontes.dto.UserDTO;
import com.davidFontes.services.UserService;

@RestController
@RequestMapping(value="/users")
public class UserResource {

	@Autowired
	private UserService service;
	
	@GetMapping
	public ResponseEntity<List<UserDTO>> buscaTodos() {
		List<User> lista = service.buscaTodos();	
		List<UserDTO> listaDTO = lista.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
		
		return ResponseEntity.ok().body(listaDTO);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<UserDTO> buscaPorId(@PathVariable String id) {
		User user = service.buscaPorId(id);	
		return ResponseEntity.ok().body(new UserDTO(user));
	}
	
	@PostMapping
	public ResponseEntity<Void> inserir(@RequestBody UserDTO objDTO){
		
		User obj = service.userDTOParaUser(objDTO);
		obj = service.inserir(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void>deletePorId(@PathVariable String id) {
		service.delete(id);	
		return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Void> update(@RequestBody UserDTO objDTO, @PathVariable String id){
		
		User obj = service.userDTOParaUser(objDTO);
		obj.setId(id);
		obj = service.atualiza(obj);
		
		return ResponseEntity.noContent().build();
	}
	
	@GetMapping("/{id}/posts")
	public ResponseEntity<List<Post>> buscaPosts(@PathVariable String id) {
		User user = service.buscaPorId(id);	
		return ResponseEntity.ok().body(user.getPosts());
	}
}
