package com.davidFontes.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.davidFontes.domain.Post;
import com.davidFontes.services.PostService;

@RestController
@RequestMapping(value="/posts")
public class PostResource {

	@Autowired
	private PostService service;
	
	@GetMapping("/{id}")
	public ResponseEntity<Post> buscaPorId(@PathVariable String id) {
		Post obj = service.buscaPorId(id);	
		return ResponseEntity.ok().body(obj);
	}
}
