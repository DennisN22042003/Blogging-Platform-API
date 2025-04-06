package com.example.bloggingplatformAPI.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.bloggingplatformAPI.repositories.BlogRepository;

@Controller
@RequestMapping("/api/v1/")
public class BlogController {
    private BlogRepository blogRepository;

    // TODO: Set up endpoints to POST, GET, PUT, DELETE a blog post...
}
