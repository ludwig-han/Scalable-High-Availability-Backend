package com.example.demo.repository;

import com.example.demo.domain.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository {
    // 30만 건 데이터 중 와일드카드 풀 스캔을 유도하여 병목을 일으킬 핵심 메서드
    Page findByTitleContaining(String keyword, Pageable pageable);
}