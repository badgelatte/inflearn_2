package com.apiece.springboot_twitter;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestController
public class PostController {

    @GetMapping("/posts")
    public Post getPosts() {
        return new Post(1L, "안녕하세요", LocalDateTime.now());
    }
}
