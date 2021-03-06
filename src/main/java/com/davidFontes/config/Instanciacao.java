package com.davidFontes.config;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.TimeZone;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.davidFontes.domain.Post;
import com.davidFontes.domain.User;
import com.davidFontes.dto.AutorDTO;
import com.davidFontes.dto.ComentarioDTO;
import com.davidFontes.repository.PostRepository;
import com.davidFontes.repository.UserRepository;

@Configuration
public class Instanciacao implements CommandLineRunner {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	public void run(String... arg0) throws Exception {
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		userRepository.deleteAll();
		postRepository.deleteAll();
		
		
		User maria = new User(null, "Maria Brown", "maria@gmail.com");
		User alex = new User(null, "Alex Green", "alex@gmail.com");
		User bob = new User(null, "Bob Grey", "bob@gmail.com");
		
		userRepository.saveAll(Arrays.asList(maria, alex, bob));
		
		Post post1 = new Post(null, sdf.parse("21/03/2018"), "Partiu viagem", "Vou viajar para São Paulo. Abraços!", new AutorDTO(maria));
		Post post2 = new Post(null, sdf.parse("23/03/2018"), "Bom dia", "Acordei feliz hoje!", new AutorDTO(maria));
		
		ComentarioDTO com1= new ComentarioDTO("Boa viagem mano!", sdf.parse("21/03/2018"), new AutorDTO(alex));
		ComentarioDTO com2= new ComentarioDTO("Aproveite!", sdf.parse("22/03/2018"), new AutorDTO(bob));
		ComentarioDTO com3= new ComentarioDTO("Tenha um ótimo dia!", sdf.parse("21/03/2018"), new AutorDTO(alex));
		
		post1.getComentarios().addAll(Arrays.asList(com1, com2));
		post2.getComentarios().add(com3);
		
		postRepository.saveAll(Arrays.asList(post1, post2));
		
		maria.getPosts().addAll(Arrays.asList(post1, post2));
		userRepository.save(maria);
	}
}
