package com.example.demo.controller;

import com.example.demo.domain.Post;
import com.example.demo.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final PostRepository postRepository;

    @GetMapping("/api/posts")
    public Page<Post> getPosts(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "test") String keyword) {

        // PK 인덱스로 정렬하되, Containing(LIKE %..%) 조건으로 DB 부하 유도
        PageRequest pageRequest = PageRequest.of(page, size, Sort.by("id").descending());
        return postRepository.findByTitleContaining(keyword, pageRequest);
    }
}