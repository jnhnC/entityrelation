package com.example.entityrelation.api;

import com.example.entityrelation.domain.CategoryItem;
import com.example.entityrelation.repository.CategoryItemRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
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
    public List<CategoryItemDto> categoryItemList(){
        List<CategoryItem> categoryItems = categoryItemRepository.findAll();
        return categoryItems.stream().map(CategoryItemDto::new).collect(toList());
    }

    @Data
    private class CategoryItemDto {
        private Long id;
        private String dtype;
        private String categoryName;
        private String itemName;

        public CategoryItemDto(CategoryItem categoryItem){
            id = categoryItem.getId();
            dtype = categoryItem.getItems().getDtype();
            categoryName = categoryItem.getCategories().getName();
            itemName = categoryItem.getItems().getName();
        }
    }
}
