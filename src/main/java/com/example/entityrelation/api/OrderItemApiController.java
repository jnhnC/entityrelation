package com.example.entityrelation.api;

import com.example.entityrelation.domain.OrderItem;
import com.example.entityrelation.repository.OrderItemRepository;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class OrderItemApiController {

    private final OrderItemRepository orderItemRepository;

    @GetMapping("/api/orderItemList")
    public Page<OrderItemDto> orderItemList(Pageable pageable){
        return orderItemRepository.findAll(pageable).map(OrderItemDto::new);

    }

    @Data
    private class OrderItemDto {
        private String itemName;
        private int count;
        private Long id;
        private LocalDateTime orderDate;
        private int orderPrice;

        public OrderItemDto(OrderItem orderItem){
            itemName = orderItem.getItem().getName();
            count = orderItem.getCount();
            id = orderItem.getId();
            orderDate = orderItem.getOrder().getOrderDate();
            orderPrice = orderItem.getOrderPrice();

        }

    }
}
