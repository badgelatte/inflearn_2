package com.apiece.springboot_twitter.comment;

import com.apiece.springboot_twitter.post.Post;
import com.apiece.springboot_twitter.post.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final PostRepository postRepository;

    public Comment createComment(Long postId, Comment request) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        Comment comment = Comment.builder()
                .content(request.getContent())
                .postId(postId)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        Comment newComment = commentRepository.save(comment);

        post.increaseCommentCount();
        postRepository.save(post);

        return newComment;
    }

    public List<Comment> getComments(Long postId) {
        return commentRepository.findByPostIdOrderByIdDesc(postId);
    }

    public Comment updatedComment(Long postId, Long commentId, Comment request) {
        Comment comment = commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));
        comment.updateContent(request.getContent());

        // createComment의 save는 insert 쿼리 발생, 여기서는 update 쿼리 발생(기존에 이미 있는 것이기 때문)
        return commentRepository.save(comment);
    }

    public void deleteComment(Long postId, Long commentId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        Comment comment = commentRepository.findByIdAndPostId(commentId, postId)
                .orElseThrow(() -> new IllegalArgumentException("댓글을 찾을 수 없습니다."));

        post.decreaseCommentCount();

        commentRepository.delete(comment);
        postRepository.save(post);
    }
}
