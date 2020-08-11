package com.example.entityrelation.domain.item;

import com.example.entityrelation.domain.Category;
import com.example.entityrelation.domain.CategoryItem;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Getter @Setter
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantity;

    @OneToMany(mappedBy = "items")
    private List<CategoryItem> categoryItems = new ArrayList<>();

}
