package com.example.entityrelation.domain;


import lombok.*;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString(of = {"id", "status"})
public class Delivery extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "delivery_id")
    private Long id;

    //Enum을 이용한 상태값 지정
    // private String status;
    @Enumerated
    private DeliveryStatus status;

    //일대일 양방향 매핑 관계 설정
    @OneToOne(mappedBy = "delivery")
    private Order order;

    public Delivery(Long id) {
        this.id = id;
    }


    public Delivery(DeliveryStatus status) {
        this.status = status;
    }


}
