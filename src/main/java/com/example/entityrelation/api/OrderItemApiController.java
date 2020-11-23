package com.example.entityrelation.api;

import com.example.entityrelation.domain.OrderItem;
import com.example.entityrelation.repository.OrderItemRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.time.LocalDateTime;

@RestController
@RequiredArgsConstructor
public class OrderItemApiController {

    private final OrderItemRepository orderItemRepository;

    @GetMapping("/api/orderItemList")
    public Page<OrderItemDto> orderItemList(Pageable pageable) {
        return orderItemRepository.findAll(pageable).map(OrderItemDto::new);

    }

    @PostMapping("/api/orderItems")
    public OrderItemDto saveOrderItems(@RequestBody @Valid OrderItemRequest request){
       return null;// OrderItem orderItem = new OrderItem(requst)
    }


    @Data
    private class OrderItemDto {
        private String itemName;
        private int count;
        private Long id;
        private LocalDateTime orderDate;
        private int orderPrice;

        public OrderItemDto(OrderItem orderItem) {
            itemName = orderItem.getItem().getName();
            count = orderItem.getCount();
            id = orderItem.getId();
            orderDate = orderItem.getOrder().getOrderDate();
            orderPrice = orderItem.getOrderPrice();

        }

    }

    private class OrderItemRequest {
    }
}
