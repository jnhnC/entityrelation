package com.example.entityrelation.domain;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Delivery {

    @Id
    @GeneratedValue
    @Column(name="delivery_id")
    private Long id;

    //Enum을 이용한 상태값 지정
   // private String status;
    @Enumerated
    private DeliveryStatus status;

    //일대일 양방향 매핑 관계 설정
    @OneToOne(mappedBy = "delivery")
    private Order order;


    public Delivery(DeliveryStatus status) {
        this.status = status;
    }
}
