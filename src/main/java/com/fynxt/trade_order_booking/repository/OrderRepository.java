package com.fynxt.trade_order_booking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fynxt.trade_order_booking.entity.Order;
import com.fynxt.trade_order_booking.entity.Status;

public interface OrderRepository extends JpaRepository<Order, Long> {

    long countByTraderIdAndStatus(String traderId, Status status);
}