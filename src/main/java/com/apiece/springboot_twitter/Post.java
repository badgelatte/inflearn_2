package com.apiece.springboot_twitter;

import java.time.LocalDateTime;

// PostRequest를 사용하기도 함
public record Post(
        Long id,
        String content,
        LocalDateTime createdAt
) {
    // recored는 setter는 없으니 새로 만들어야한다
    public Post updateContent(String content) {
//        this.content = content; -> 이는 불가, content가 final 필드로 사용되기 때문
        return new Post(this.id, content, this.createdAt);
    }
}
