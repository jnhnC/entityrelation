package com.example.entityrelation.api;

import com.example.entityrelation.domain.OrderItem;
import com.example.entityrelation.repository.OrderItemRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class OrderItemApiController {

    private final OrderItemRepository orderItemRepository;

    @GetMapping("/api/orderItemList")
    public List<OrderItem> orderItemList(){
        return orderItemRepository.findAll();
    }

}
