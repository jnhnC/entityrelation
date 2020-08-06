package com.example.entityrelation.api;

import com.example.entityrelation.domain.Member;
import com.example.entityrelation.domain.Order;
import com.example.entityrelation.repository.MemberRepsitory;
import com.example.entityrelation.repository.OrderRepsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderApiController {
    private final MemberRepsitory memberRepsitory;
    private final OrderRepsitory orderRepsitory;


    //주인 엔티티 Order 불러 오기
    @GetMapping("/api/orders")
    public List<Order> orders(){
        init();
        return orderRepsitory.findAll();
    }


    //데이터를 초기화를 위한 함수
    private void init() {

        Member member = new Member();
        member.setName("testA");
        memberRepsitory.save(member);

        Member member2 = new Member();
        member2.setName("testB");
        memberRepsitory.save(member2);


        Order order = new Order();
        order.setMember(member);
        order.setOrderDate(LocalDateTime.now());
        orderRepsitory.save(order);

        Order order2 = new Order();
        order2.setMember(member);
        order2.setOrderDate(LocalDateTime.now());
        orderRepsitory.save(order2);

        Order order3 = new Order();
        order3.setMember(member2);
        order3.setOrderDate(LocalDateTime.now());
        Order save = orderRepsitory.save(order3);
    }
}
