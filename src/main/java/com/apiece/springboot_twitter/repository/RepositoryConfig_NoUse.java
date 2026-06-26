package com.apiece.springboot_twitter.repository;

import com.apiece.springboot_twitter.post.PostRepository;

//@Configuration
public class RepositoryConfig_NoUse {

//    @Bean // 원래는 (name = "postRepository")로 지정하나 없으면 메서드명을 따라간다
    public PostRepository postRepository(PostRepository jpaPostRepository) {
        return jpaPostRepository;
    }
}
