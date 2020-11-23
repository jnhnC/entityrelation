package com.example.entityrelation.api;


import com.example.entityrelation.domain.Category;
import com.example.entityrelation.domain.CategoryItem;
import com.example.entityrelation.domain.item.Item;
import com.example.entityrelation.repository.CatogoryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class CategoryApiController {
    private final CatogoryRepository catogoryRepository;


    @GetMapping("/api/categroy")
    public Page<CategoryDto> categoryDtoList(Pageable pageable) {
        return catogoryRepository.findAll(pageable).map(CategoryDto::new);
    }

    @Data
    private class CategoryDto {
        private String categoryName;
        private List<CategoryItemDto> categoryItems;

        public CategoryDto(Category category) {
            categoryName = category.getName();
            categoryItems = category.getCategoryItems().stream().map(CategoryItemDto::new).collect(toList());
        }

        @Data
        private class CategoryItemDto {
            private String itemName;

            public CategoryItemDto(CategoryItem categoryItem) {
                itemName = categoryItem.getItem().getName();

            }
        }
    }
}
