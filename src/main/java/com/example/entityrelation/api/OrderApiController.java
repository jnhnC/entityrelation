package com.example.entityrelation.api;

import com.example.entityrelation.domain.DeliveryStatus;
import com.example.entityrelation.domain.Member;
import com.example.entityrelation.domain.Order;
import com.example.entityrelation.domain.OrderItem;
import com.example.entityrelation.domain.item.Item;
import com.example.entityrelation.repository.MemberRepsitory;
import com.example.entityrelation.repository.OrderQueryRepository;
import com.example.entityrelation.repository.OrderRepsitory;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class OrderApiController {
    private final MemberRepsitory memberRepsitory;
    private final OrderRepsitory orderRepsitory;
    private final OrderQueryRepository orderQueryRepository;


    //주인 엔티티 Order 불러 오기
    @GetMapping("/api/orders")
    public List<Order> orders(){
        return orderRepsitory.findAll();
    }


    //DTO 사용하기
    @GetMapping("/api/ordersDto")
    public List<OrderDto> ordersDto(){
        List<Order> orders =orderRepsitory.findFetchOrderMember();

        List<OrderDto> orderDtos = orders.stream()
                .map(OrderDto::new)
                .collect(toList());
        return orderDtos;
    }

    //페치조인 사용하기
    @GetMapping("/api/ordersFetch")
    public List<OrderDto> ordersFetch(){
        List<Order> orders =orderRepsitory.findFetchAll();

        List<OrderDto> orderDtos = orders.stream()
                .map(OrderDto::new)
                .collect(toList());
        return orderDtos;
    }

    //페이징 변경
    @GetMapping("/api/ordersPage")
    public Page<OrderDto> ordersPage(){

        PageRequest pageRequest = PageRequest.of(0, 3, Sort.by(Sort.Direction.DESC, "member_id"));
        Page<Order> orders = orderRepsitory.findAll(pageRequest);
        Page<OrderDto> orderDtos = orders.map(m -> new OrderDto(m));


        return orderDtos;
    }

    //다중페치조인 페이징
    @GetMapping("/api/ordersFetchPaging")
    public List<OrderDto> ordersFetchPaging(
            @RequestParam(value = "offset", defaultValue = "0") int offset,
            @RequestParam(value = "limit", defaultValue = "100") int limit ){

        List<Order> orders =orderQueryRepository.findFetchPaging(offset, limit);

        List<OrderDto> orderDtos = orders.stream()
                .map(OrderDto::new)
                .collect(toList());
        return orderDtos;
    }

    @Data
    static class OrderDto{
        private Long orderId;
        private String name;
        private String address;
        private DeliveryStatus status;
        private List<OrderItemDto> orderItems ;

        public OrderDto(Order order){
            orderId = order.getId();
            name = order.getMember().getName();
            address = order.getMember().getAddress().getCity()
                    +""+ order.getMember().getAddress().getStreet();
            status = order.getDelivery().getStatus();
            orderItems =  order.getOrderItems().stream()
                    .map(OrderItemDto::new)
                    .collect(toList());

        }

        @Data
        static class OrderItemDto {

            private Long id;
            private int orderPrice;
            private String itemName ;

            public OrderItemDto(OrderItem orderItem){
                id = orderItem.getId();
                orderPrice = orderItem.getOrderPrice();
                itemName = orderItem.getItem().getName();
            }

        }
    }

}
