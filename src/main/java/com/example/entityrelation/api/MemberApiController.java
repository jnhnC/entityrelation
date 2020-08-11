package com.example.entityrelation.api;

import com.example.entityrelation.domain.*;
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

    //default_batch_fetch_size옵션을 이용한 컬렉션 최적화
    @GetMapping("/api/membersCollectionFetch")
    public List<MemberDto> membersCollectionFetch(){
        List<Member> members = memberRepsitory.findAll();
        List<MemberDto> result = members.stream().map(MemberDto::new)
                .collect(toList());

        return result;
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

        @Data
        private class OrderItemDto {
            private String itemName;

            public OrderItemDto(OrderItem orderItem){
                itemName = orderItem.getItem().getName();
            }

        }
    }

}
