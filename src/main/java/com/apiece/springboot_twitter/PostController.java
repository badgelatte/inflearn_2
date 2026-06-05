package com.apiece.springboot_twitter;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class PostController {

    private Map<Long, Post> posts = new HashMap<>();
    private AtomicLong idGenereator = new AtomicLong(1);
    // 멀티 스레드 사용해서 여러 스레드가 다 드러어오면 AtomicLong은 동시에 들어와도 먼저 들어온 연산을 먼저 하는 식으로 순차적으로 끝냄

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/posts")
    public Post createPost(@RequestBody Post post) {
        long newId = idGenereator.getAndIncrement();
        Post newPost = new Post(newId, post.getContent(), LocalDateTime.now());

        posts.put(newId, newPost);

        return newPost;
    }

    @GetMapping("/api/posts")
    public List<Post> getAllPosts() {
        return new ArrayList<>(posts.values());
    }

    // id가 없을 시 null로 반환
    @GetMapping("/api/posts/{id}")
    public Post getPost(@PathVariable Long id) {
        return posts.get(id);
    }

    @PutMapping("/api/posts/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post postRequest) {
        Post post = posts.get(id);
        post.updateContent(postRequest.getContent());

        posts.put(id, post);

        return post;
    }

    @DeleteMapping("/api/posts/{id}")
    public void deletePost(@PathVariable Long id) {
        posts.remove(id);
    }

    // /api/posts/search?page=1&size=3
    @GetMapping("/api/posts/search")
    public List<Post> searchPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        return posts.values()
                .stream()
                .sorted((p1, p2) -> Long.compare(p2.getId(), p1.getId()))
                .skip((long) page * size)
                .limit(size)
                .toList();
        // sorted -> p2와 p1의 아이디를 비교하여 더 큰 쪽으로 우선 정렬 = 내림차순 정렬
        // skip -> 메소드 안의 개수의 요소를 건너띄우고 그 다음부터 반환하겠다
        // limit -> 원하는 사이즈만 반환하기
    }

}
