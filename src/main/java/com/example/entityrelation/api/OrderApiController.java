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
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class OrderApiController {
    private final MemberRepsitory memberRepsitory;
    private final OrderRepsitory orderRepsitory;


    //주인 엔티티 Order 불러 오기
    @GetMapping("/api/orders")
    public List<Order> orders(){
        return orderRepsitory.findAll();
    }


    //DTO 사용하기
    @GetMapping("/api/ordersDto")
    public List<OrderDto> ordersDto(){
        List<Order> orders =orderRepsitory.findAll();

        List<OrderDto> orderDtos = orders.stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());
        return orderDtos;
    }

    //페치조인 사용하기
    @GetMapping("/api/ordersFetch")
    public List<OrderDto> ordersFetch(){
        List<Order> orders =orderRepsitory.findFetchAll();

        List<OrderDto> orderDtos = orders.stream()
                .map(OrderDto::new)
                .collect(Collectors.toList());
        return orderDtos;
    }


    @Data
    static class OrderDto{
        private Long orderId;
        private String name;

        public OrderDto(Order order){
            orderId = order.getId();
            name = order.getMember().getName();

        }

    }

}
