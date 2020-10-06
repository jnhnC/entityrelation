package com.example.entityrelation.api;

import com.example.entityrelation.domain.CategoryItem;
import com.example.entityrelation.repository.CategoryItemRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class CategoryItemApiController {
    private final CategoryItemRepository categoryItemRepository;

    @GetMapping("/api/categerItem")
    public Page<CategoryItemDto> categoryItemList(Pageable pageable){
        return  categoryItemRepository.findAll(pageable).map(CategoryItemDto::new);
    }

    @Data
    private class CategoryItemDto {
        private Long id;
        private String dtype;
        private String categoryName;
        private String itemName;

        public CategoryItemDto(CategoryItem categoryItem){
            id = categoryItem.getId();
            dtype = categoryItem.getItem().getDtype();
            categoryName = categoryItem.getCategory().getName();
            itemName = categoryItem.getItem().getName();
        }
    }
}
