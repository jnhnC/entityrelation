package com.example.entityrelation.repository;

import com.example.entityrelation.domain.CategoryItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryItemRepository extends JpaRepository<CategoryItem,Long> {
}
