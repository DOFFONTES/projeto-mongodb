package com.davidFontes.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.davidFontes.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{
	
	@Query("{ 'titulo': { $regex: ?0, $options: 'i' } }")
	List<Post> buscaPorTitulo(String texto);
	
	@Query("{ $and: [ { data: {$gte: ?1} }, { data: { $lte: ?2} }, { $or: [ { 'titulo': { $regex: ?0, $options: 'i' } }, { 'conteudo': { $regex: ?0, $options: 'i' } }, { 'comentarios.texto': { $regex: ?0, $options: 'i' } } ] } ] }")
	List<Post> buscaCompleta(String texto, Date minData, Date maxData); 

	List<Post> findByTituloContainingIgnoreCase(String texto);
}
