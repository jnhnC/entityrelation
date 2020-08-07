package com.example.entityrelation.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name="orders")
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
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="delivery_id")
    private Delivery delivery;

}

