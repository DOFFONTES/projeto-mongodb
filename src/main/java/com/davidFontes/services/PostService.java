package com.davidFontes.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davidFontes.domain.Post;
import com.davidFontes.repository.PostRepository;
import com.davidFontes.service.exception.ObjetoNaoEncontradoException;

@Service
public class PostService {
	
	@Autowired
	private PostRepository repo;
	
	public Post buscaPorId(String id) {
		Optional<Post> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException("Objeto não encontrado"));	
	}
	
}
