package com.example.entityrelation.domain;

import com.example.entityrelation.domain.item.Book;
import com.example.entityrelation.domain.item.Item;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class OrderItem extends BaseEntity {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    private int orderPrice;

    private int count;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    public OrderItem(Order order, int orderPrice, Item item, int count) {
        this.order = order;
        this.orderPrice = orderPrice;
        this.item = item;
        this.count =  count;
    }
}
