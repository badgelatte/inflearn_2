package com.apiece.springboot_twitter.repository;

import com.apiece.springboot_twitter.Post;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;


@Repository
public class InMemoryPostRepository implements PostRepository{

    private Map<Long, Post> posts = new HashMap<>();
    private AtomicLong idGenereator = new AtomicLong(1);
    // 멀티 스레드 사용해서 여러 스레드가 다 드러어오면 AtomicLong은 동시에 들어와도 먼저 들어온 연산을 먼저 하는 식으로 순차적으로 끝냄


    @Override
    public Post save(Post post) {
        Long id = post.getId() == null ? idGenereator.getAndIncrement() : post.getId();
        post.setId(id);
        posts.put(id, post);
        return post;
    }

    @Override
    public List<Post> findAll() {
        return new ArrayList<>(posts.values());
    }

    @Override
    public Optional<Post> findById(Long id) {
        return Optional.ofNullable(posts.get(id));
        // ofNullable : map에서 get으로 가져온 결과가 있으면 post를 반환하고 만일 없다면 null이 들어간다
    }

    @Override
    public void delteById(Long id) {
        posts.remove(id);
    }

    @Override
    public List<Post> findAllPaged(int page, int size) {
        return posts.values()
                .stream()
                .sorted((p1, p2) -> Long.compare(p2.getId(), p1.getId()))
                .skip((long) page * size)
                .limit(size)
                .toList();
    }
}
