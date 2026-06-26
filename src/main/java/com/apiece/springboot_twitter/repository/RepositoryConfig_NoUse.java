package com.apiece.springboot_twitter.repository;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class RepositoryConfig_NoUse {

//    @Bean // 원래는 (name = "postRepository")로 지정하나 없으면 메서드명을 따라간다
    public PostRepository postRepository(PostRepository jpaPostRepository) {
        return jpaPostRepository;
    }
}
