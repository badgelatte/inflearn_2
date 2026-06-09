package com.apiece.springboot_twitter;

import com.apiece.springboot_twitter.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor // final인 필드에 대해서 이것을 하나의 필드로 가지는 생성자를 만들어 준다
public class PostController {

    private final PostRepository postRepository;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/api/posts")
    public Post createPost(@RequestBody Post post) {
        Post newPost = new Post(null, post.getContent(), LocalDateTime.now());

        postRepository.save(newPost);

        return newPost;
    }

    @GetMapping("/api/posts")
    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    // id가 없을 시 null로 반환
    @GetMapping("/api/posts/{id}")
    public Post getPost(@PathVariable Long id) {
        return postRepository.findById(id)
                .orElseThrow();
    }

    @PutMapping("/api/posts/{id}")
    public Post updatePost(@PathVariable Long id, @RequestBody Post postRequest) {
        return postRepository.findById(id)
                .map(post -> {
                    post.updateContent(postRequest.getContent());
                    return postRepository.save(post);
                })
                .orElseThrow();
        // postRepository.findById로 조회한 다음 가져온 객체를 맵핑함
        // 맵핑은 updateContent를 이용
        // 요청 값으로 들고온 postRequest에 content를 여기다 바로 넣어주고 update
    }

    @DeleteMapping("/api/posts/{id}")
    public void deletePost(@PathVariable Long id) {
        postRepository.deleteById(id);
    }

    // /api/posts/search?page=1&size=3
    @GetMapping("/api/posts/search")
    public List<Post> searchPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "3") int size
    ) {
        return postRepository.findAllPaged(page, size);
    }

}
