package com.example.entityrelation.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name="orders")
@NoArgsConstructor
public class Order {

    @Id    @GeneratedValue
    @Column(name="order_id")
    private Long id;

    private LocalDateTime orderDate;

    //Member와 다대일 매핑
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name ="member_id")
    private Member member;

    //Delivery와 일대일 매핑
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();


    public Order(Member member, Delivery delivery, LocalDateTime orderDate) {
        this.member = member;
        this.delivery = delivery;
        this.orderDate = orderDate;
    }
}

