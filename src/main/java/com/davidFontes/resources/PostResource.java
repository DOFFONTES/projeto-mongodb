package com.davidFontes.resources;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.davidFontes.domain.Post;
import com.davidFontes.resources.util.URL;
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
	
	@GetMapping("/titulo")
	public ResponseEntity<List<Post>> buscaPorTitulo(@RequestParam(value="texto", defaultValue = "") String texto) {
		System.out.println(texto);
		texto = URL.decodeURL(texto);
		System.out.println(texto);
		List<Post> posts = service.buscaPorTitulo(texto);	
		return ResponseEntity.ok().body(posts);
	}
	
	@GetMapping("/buscacompleta")
	public ResponseEntity<List<Post>> buscaCompleta(
			@RequestParam(value="texto", defaultValue = "") String texto,
			@RequestParam(value="minData", defaultValue = "") String minData,
			@RequestParam(value="maxData", defaultValue = "") String maxData) {
		
		texto = URL.decodeURL(texto);
		Date min = URL.convertData(minData, new Date(0L));
		Date max = URL.convertData(maxData, new Date());
		
		List<Post> posts = service.buscaCompleta(texto, min, max);	
		return ResponseEntity.ok().body(posts);
	}
}








