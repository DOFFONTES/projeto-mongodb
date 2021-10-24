package com.davidFontes.resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davidFontes.domain.User;

@RestController
@RequestMapping(value="/users")
public class UserResource {

	
	@GetMapping
	public ResponseEntity<List<User>> buscaTodos() {
		User maria = new User("1001", "Maria Brown", "maria@gmail.com");
		User joao = new User("1002", "João Green", "joao@gmail.com");
		List<User> lista = new ArrayList<>();
		
		lista.addAll(Arrays.asList(maria,joao));
		
		return ResponseEntity.ok().body(lista);
	}
}
