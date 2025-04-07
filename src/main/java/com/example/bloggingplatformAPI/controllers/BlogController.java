package com.example.bloggingplatformAPI.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.example.bloggingplatformAPI.repositories.BlogRepository;
import com.example.bloggingplatformAPI.models.BlogPost;

import java.util.ArrayList;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1/")
public class BlogController {
    private final BlogRepository blogRepository;

    public BlogController(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @PostMapping("create/post")
    public ResponseEntity<BlogPost> createBlogPost(@RequestBody BlogPost blogPost) {
        blogRepository.save(blogPost);
        return ResponseEntity.status(HttpStatus.CREATED).body(blogPost);
    }

    @PutMapping("update/post/{id}")
    public ResponseEntity<BlogPost> updateBlogPost(@PathVariable Long id) {
        Optional<BlogPost> optionalBlogPost = blogRepository.findById(id);
        if (optionalBlogPost.isPresent()) {
            return ResponseEntity.ok(optionalBlogPost.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("delete/post/{id}")
    public ResponseEntity<String> deleteBlogPost(@PathVariable Long id) {
        if (blogRepository.existsById(id)) {
            blogRepository.deleteById(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("fetch/post/{id}")
    public ResponseEntity<BlogPost> fetchBlogPost(@PathVariable Long id) {
        Optional<BlogPost> optionalBlogPost = blogRepository.findById(id);
        if (optionalBlogPost.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(optionalBlogPost.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("fetch-all/post/")
    public ResponseEntity<ArrayList<BlogPost>> fetchAllBlogPost() {
        ArrayList<BlogPost> optionalBlogPosts = (ArrayList<BlogPost>) blogRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(optionalBlogPosts);
    }
}
