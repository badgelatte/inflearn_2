package com.apiece.springboot_twitter;

import java.time.LocalDateTime;


public record Post(
        Long id,
        String content,
        LocalDateTime createdAt
) {
}
