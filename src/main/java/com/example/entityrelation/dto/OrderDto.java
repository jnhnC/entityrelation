package com.example.entityrelation.dto;

import com.example.entityrelation.api.OrderApiController;
import com.example.entityrelation.domain.DeliveryStatus;
import com.example.entityrelation.domain.Order;
import com.example.entityrelation.domain.OrderItem;
import com.querydsl.core.annotations.QueryProjection;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Data
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class OrderDto{
    private Long orderId;
    private String name;
    private String city;
    private DeliveryStatus status;
    private List<OrderItemDto> orderItems ;

    @QueryProjection
    public OrderDto(Order order){
        orderId = order.getId();
        name = order.getMember().getName();
        city = order.getMember().getAddress().getCity();
        status = order.getDelivery().getStatus();
        orderItems =  order.getOrderItems().stream()
                .map(OrderDto.OrderItemDto::new)
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
