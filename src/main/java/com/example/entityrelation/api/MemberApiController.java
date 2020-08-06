package com.example.entityrelation.api;

import com.example.entityrelation.domain.Member;
import com.example.entityrelation.domain.Order;
import com.example.entityrelation.repository.MemberRepsitory;
import com.example.entityrelation.repository.OrderRepsitory;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberRepsitory memberRepsitory;
    private final OrderRepsitory orderRepsitory;


    //가짜 엔티티 Member 불러 오기
    @GetMapping("/api/members")
    public List<Member> orders(){
        init();
        return memberRepsitory.findAll();
    }

    //DTO를 이용한 가짜 엔티티 member 불러오기
    @GetMapping("/api/membersCollection")
    public List<MemberDto> membersCollection(){
        init();
        List<Member> members = memberRepsitory.findAll();
        List<MemberDto> result = members.stream().map(MemberDto::new)
                .collect(toList());

        return result;
    }

    @Data
    static class MemberDto {
        private String name;
        private List<OrderDto> orders ;

        public MemberDto(Member member){
            name = member.getName();
            orders = member.getOrders().stream()
                    .map(orders-> new OrderDto(orders))
                    .collect(toList());
            for(Order order :  member.getOrders()){
                System.out.println(order.getId());
                System.out.println(order.getOrderDate());

            }
        }
    }
    @Data
    static class OrderDto {
        private Long orderId;
        private LocalDateTime orderDate;

        public OrderDto(Order order) {
            orderId = order.getId();
            orderDate = order.getOrderDate();

        }
    }


    private void init() {

        //------------------------------------------
        Member member = new Member();
        member.setName("testA");
        memberRepsitory.save(member);

        Member member2 = new Member();
        member2.setName("testB");
        memberRepsitory.save(member2);

        //------------------------------------------

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
