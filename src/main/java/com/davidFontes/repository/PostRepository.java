package com.davidFontes.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.davidFontes.domain.Post;

@Repository
public interface PostRepository extends MongoRepository<Post, String>{

}
