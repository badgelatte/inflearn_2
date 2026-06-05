package com.apiece.springboot_twitter;

import java.time.LocalDateTime;

// PostRequest를 사용하기도 함
public record Post(
        Long id,
        String content,
        LocalDateTime createdAt
) {
}
