package com.example.bloggingplatformAPI.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;

import com.example.bloggingplatformAPI.repositories.BlogRepository;
import com.example.bloggingplatformAPI.models.BlogPost;
import com.example.bloggingplatformAPI.Services.BlogService;

import java.util.ArrayList;
import java.util.Optional;
import java.util.UUID;

@Controller
@RequestMapping("/api/v1/")
public class BlogController {
    private final BlogRepository blogRepository;
    private final BlogService blogService;

    public BlogController(BlogRepository blogRepository, BlogService blogService) {
        this.blogRepository = blogRepository;
        this.blogService = blogService;
    }

    @PostMapping("create/post")
    public ResponseEntity<?> createBlogPost(
            @RequestParam String title,
            @RequestParam String content,
            @RequestParam String category
    ) {
        BlogPost blogPost = blogService.createNewBlogPost(title, content, category);
        return ResponseEntity.status(HttpStatus.CREATED).body(blogPost);
    }

    @PutMapping("update/post/{id}")
    public ResponseEntity<?> updateBlogPost(@PathVariable String id) {
        Optional<BlogPost> optionalBlogPost = blogRepository.findById(Long.valueOf(id));
        if (optionalBlogPost.isPresent()) {
            return ResponseEntity.ok(optionalBlogPost.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("delete/post/{id}")
    public ResponseEntity<?> deleteBlogPost(@PathVariable String id) {
        if (blogRepository.existsById(Long.valueOf(id))) {
            blogRepository.deleteById(Long.valueOf(id));
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("fetch/post/{id}")
    public ResponseEntity<?> fetchBlogPost(@PathVariable String id) {
        Optional<BlogPost> optionalBlogPost = blogRepository.findById(Long.valueOf(id));
        if (optionalBlogPost.isPresent()) {
            return ResponseEntity.status(HttpStatus.OK).body(optionalBlogPost.get());
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("fetch-all/post/")
    public ResponseEntity<?> fetchAllBlogPosts() {
        ArrayList<BlogPost> optionalBlogPosts = (ArrayList<BlogPost>) blogRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK).body(optionalBlogPosts);
    }
}
