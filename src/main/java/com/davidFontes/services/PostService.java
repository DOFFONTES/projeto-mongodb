package com.davidFontes.services;

import java.util.Date;
import java.util.List;
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
	
	public List<Post> buscaCompleta(String texto, Date minData, Date maxData){
		maxData = new Date(maxData.getTime() + 24 * 60 * 60 * 1000);
		return repo.buscaCompleta(texto, minData, maxData);
	}
	
	public List<Post> buscaPorTitulo(String texto){
		return repo.buscaPorTitulo(texto);
	}
	
}
