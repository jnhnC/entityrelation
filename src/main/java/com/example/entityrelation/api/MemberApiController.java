package com.example.entityrelation.api;

import com.example.entityrelation.domain.Member;
import com.example.entityrelation.domain.Order;
import com.example.entityrelation.repository.MemberRepsitory;
import com.example.entityrelation.repository.OrderRepsitory;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberRepsitory memberRepsitory;
    private final OrderRepsitory orderRepsitory;

    //가짜 엔티티 member 불러 오기
    @GetMapping("/api/members")
    public List<Member> members(){
        Member member = new Member();
        member.setName("testA");
        memberRepsitory.save(member);

        Order order = new Order();
        order.setMember(member);
        orderRepsitory.save(order);

        return memberRepsitory.findAll();
    }

    //주인 엔티티 Order 불러 오기
    @GetMapping("/api/orders")
    public List<Order> orders(){
        Member member = new Member();
        member.setName("testA");
        memberRepsitory.save(member);

        Order order = new Order();
        order.setMember(member);
        orderRepsitory.save(order);

        return orderRepsitory.findAll();
    }


}
