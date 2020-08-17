package com.example.entityrelation.repository;

import com.example.entityrelation.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CatogoryRepository extends JpaRepository<Category,Long> {
}
