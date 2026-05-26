package com.example.demo.repository;

import com.example.demo.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
    // 인덱스가 없는 무거운 문자열 Like 검색을 유도하여 DB I/O 부하 발생
    List<Item> findByDescriptionContainingOrderByPriceDesc(String keyword);
}