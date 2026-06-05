package com.apiece.springboot_twitter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class Post {
    private Long id;
    private String content;
    private LocalDateTime createdAt;

    public void updateContent(String content) {
        this.content = content;
    }
}
