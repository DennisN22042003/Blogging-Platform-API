package com.example.bloggingplatformAPI.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.bloggingplatformAPI.models.BlogPost;
import com.example.bloggingplatformAPI.repositories.BlogRepository;

import java.util.UUID;

@Service
public class BlogService {

    @Autowired
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    // Create a new Blog Post
    public BlogPost createNewBlogPost(String title, String content, String category) {
        BlogPost newBlogPost = new BlogPost();
        newBlogPost.setId(UUID.randomUUID().toString());
        newBlogPost.setTitle(title);
        newBlogPost.setContent(content);
        newBlogPost.setCategory(category);

        // Save the new Blog Post in MongoDB
        BlogPost createdBlogPost = blogRepository.save(newBlogPost);
        return createdBlogPost;
    }
}
