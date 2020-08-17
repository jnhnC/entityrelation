package com.example.entityrelation.repository;

import com.example.entityrelation.domain.Delivery;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeliveryRepository extends JpaRepository<Delivery, Long> {

}
