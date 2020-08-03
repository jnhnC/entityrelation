package com.example.entityrelation.repository;

import com.example.entityrelation.domain.Member;
import com.example.entityrelation.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepsitory extends JpaRepository<Order,Long> {

}
