package com.apiece.springboot_twitter;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // IDENTITY = DB 컬럼 설정에 맞게 JPA가 따라간다
    private Long id;

    private String content;

    private LocalDateTime createdAt;

    public void updateContent(String content) {
        this.content = content;
    }
}
