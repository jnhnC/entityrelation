package com.example.entityrelation.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Category extends BaseEntity{

    @Id
    @GeneratedValue
    private Long id;
    private String name;


    @OneToMany(mappedBy = "category")
    private List<CategoryItem> categoryItems = new ArrayList<>();


    public Category(String name) {
        this.name = name;
    }
}
