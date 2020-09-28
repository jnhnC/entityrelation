package com.example.entityrelation.api;

import com.example.entityrelation.domain.*;
import com.example.entityrelation.repository.MemberRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberRepository memberRepsitory;

    //가짜 엔티티 Member 불러 오기
    @GetMapping("/api/members")
    public List<Member> orders(){
        return memberRepsitory.findAll();
    }

    //DTO를 이용한 가짜 엔티티 member 불러오기
    @GetMapping("/api/membersCollection")
    public List<MemberDto> membersCollection(){
        List<Member> members = memberRepsitory.findAll();
        List<MemberDto> result = members.stream().map(MemberDto::new)
                .collect(toList());

        return result;
    }

    //DTO를 이용한 가짜 엔티티 member 불러오기
    @GetMapping("/api/membersCollectionPaging")
    public Page<MemberDto> membersCollectionPaging(){
        PageRequest pageRequest = PageRequest.of(0, 3);
        String name = "testA";
        Page<Member> members = memberRepsitory.findAll(pageRequest);
        System.out.println(members.getTotalElements());
        System.out.println(members.getNumber());
        System.out.println(members.getTotalPages());
        System.out.println(members.isFirst());
        System.out.println(members.hasNext());

        Page<MemberDto> toMap = members.map(member -> new MemberDto(member));
     //   Slice<Member> members = memberRepsitory.findAll(pageRequest);

//        List<MemberDto> result = members.stream().map(MemberDto::new)
//                .collect(toList());



        return toMap;
    }

    @Data
    static class MemberDto {
        private String name;
        private Address address;

        private List<OrderDto> orders ;

        public MemberDto(Member member){
            name = member.getName();
            address = member.getAddress();
            orders = member.getOrders().stream()
                    .map(orders-> new OrderDto(orders))
                    .collect(toList());
        }
    }



    @Data
    static class OrderDto {
        private Long orderId;
        private LocalDateTime orderDate;
        private DeliveryStatus status;
        private List<OrderItemDto> orderItems;

        public OrderDto(Order order) {
            orderId = order.getId();
            orderDate = order.getOrderDate();
            status = order.getDelivery().getStatus();
            orderItems = order.getOrderItems().stream()
                    .map(OrderItemDto::new)
                    .collect(toList());
        }

    }
    @Data
    static class OrderItemDto {
        private String itemName;
        public OrderItemDto(OrderItem orderItem){
            itemName = orderItem.getItem().getName();

        }

    }

}
