package com.davidFontes.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.davidFontes.domain.User;
import com.davidFontes.repository.UserRepository;

@Service
public class UserService {
	
	@Autowired
	private UserRepository repo;

	public List<User> buscaTodos(){
		return repo.findAll();	  
	}
}
