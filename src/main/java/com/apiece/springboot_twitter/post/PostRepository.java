package com.apiece.springboot_twitter.post;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

//    페이지네이션으로 처리
//    default List<Post> findAllPaged(int page, int size) {
//        Pageable pageable = PageRequest.of(page, size, Sort.by("id").descending());
//        return findAll(pageable).getContent();
//    }

    Slice<Post> findSliceBy(Pageable pageable);
}
