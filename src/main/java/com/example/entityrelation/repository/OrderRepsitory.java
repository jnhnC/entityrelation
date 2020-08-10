package com.example.entityrelation.repository;

import com.example.entityrelation.domain.Member;
import com.example.entityrelation.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrderRepsitory extends JpaRepository<Order,Long> {

    @Query("select o from Order o join fetch o.member m join fetch o.delivery d")
    List<Order> findFetchAll();

    @Query("select o from Order o join fetch o.member m")
    List<Order> findFetchOrderMember();


}
