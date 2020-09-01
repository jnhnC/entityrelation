package com.example.entityrelation.domain;


import com.example.entityrelation.domain.item.Book;
import com.example.entityrelation.domain.item.Item;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class CategoryItem extends BaseEntity{

    @Id
    @GeneratedValue
    @Column(name= "category_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;


    public CategoryItem(Category category, Item item) {
        this.category = category;
        this.item = item;
    }
}
