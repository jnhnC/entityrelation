package com.example.entityrelation.domain.item;

import com.example.entityrelation.domain.BaseEntity;
import com.example.entityrelation.domain.CategoryItem;
import com.example.entityrelation.domain.OrderItem;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn
@NoArgsConstructor
@Getter
public abstract class Item extends BaseEntity {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    protected String name;
    protected int price;
    protected int stockQuantity;

    @Column(insertable = false, updatable = false)
    private String dtype;

    @OneToMany(mappedBy = "item")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany(mappedBy = "item")
    private List<CategoryItem> categoryItems = new ArrayList<>();

}
