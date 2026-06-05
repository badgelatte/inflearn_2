package com.apiece.springboot_twitter;

import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class PostController {

    private Map<Long, Post> posts = new HashMap<>();
    private AtomicLong idGenereator = new AtomicLong(1);
    // 멀티 스레드 사용해서 여러 스레드가 다 드러어오면 AtomicLong은 동시에 들어와도 먼저 들어온 연산을 먼저 하는 식으로 순차적으로 끝냄

    @PostMapping("/api/posts")
    public Post createPost(@RequestBody Post post) {
        long newId = idGenereator.getAndIncrement();
        Post newPost = new Post(newId, post.content(), LocalDateTime.now());

        posts.put(newId, newPost);

        return newPost;
    }

    // id가 없을 시 null로 반환
    @GetMapping("/api/posts/{id}")
    public Post getPost(@PathVariable Long id) {
        return posts.get(id);
    }

}
