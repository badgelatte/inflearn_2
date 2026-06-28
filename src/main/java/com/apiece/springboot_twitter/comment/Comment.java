package com.apiece.springboot_twitter.comment;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
// JPA가 이 클래스를 상속한 다른 proxy 객체를 사용한다 그래서 protected로 사용할 수 있도록 접근 범위 늘려줌
// JPA는 NoArgsConstructor는가 필요해서 만들었지만 개발자가 사용할 땐 AllArgsConstructor를 쓰도록 권장함
@Table(name = "comments")
@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private Long postId;

    // DB 컬럼 설정
    @Column(nullable = false)   // null이 될 수 없게끔 설정
    private LocalDateTime createdAt;

    @Column(nullable = false)
    private LocalDateTime updatedAt;

    public void updateContent(String content) {
        this.content = content;
    }
}
