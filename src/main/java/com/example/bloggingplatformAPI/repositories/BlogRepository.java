package com.example.bloggingplatformAPI.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.example.bloggingplatformAPI.models.BlogPost;

import java.util.List;

@Repository
public interface BlogRepository extends MongoRepository<BlogPost, String> {
    // TODO: Custom methods....
}
