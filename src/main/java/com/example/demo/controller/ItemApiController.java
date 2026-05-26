package com.example.demo.controller;

import com.example.demo.entity.Item;
import com.example.demo.repository.ItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class ItemApiController {

    private final ItemRepository itemRepository;

    @GetMapping("/api/v1/search")
    public ResponseEntity<List<Item>> search(@RequestParam(defaultValue = "test") String keyword) {
        // 실제 유저가 쇼핑몰에서 상품을 검색하는 자연스러운 시나리오
        List<Item> results = itemRepository.findByDescriptionContainingOrderByPriceDesc(keyword);
        return ResponseEntity.ok(results);
    }
}