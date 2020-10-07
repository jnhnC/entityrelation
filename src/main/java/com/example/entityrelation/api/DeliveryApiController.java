package com.example.entityrelation.api;

import com.example.entityrelation.domain.Delivery;
import com.example.entityrelation.domain.DeliveryStatus;
import com.example.entityrelation.repository.DeliveryRepository;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequiredArgsConstructor
public class DeliveryApiController {
    private final DeliveryRepository deliveryRepository;

    @GetMapping("/api/delivery")
    public Page<DeliveryDto> deliveryDtoList(Pageable pageable) {
        return deliveryRepository.findAll(pageable).map(DeliveryDto::new);

    }

    @Data
    private class DeliveryDto {
        private Long deliveryId;
        private DeliveryStatus status;
        private Long orderId;

        public DeliveryDto(Delivery delivery) {
            deliveryId = delivery.getId();
            status = delivery.getStatus();
            orderId = delivery.getOrder().getId();
        }

    }


}
