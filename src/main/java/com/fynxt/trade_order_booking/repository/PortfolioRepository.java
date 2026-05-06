package com.fynxt.trade_order_booking.repository;

import org.apache.el.stream.Optional;
import org.hibernate.mapping.List;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fynxt.trade_order_booking.entity.Portfolio;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    Optional<Portfolio> findByTraderIdAndStock(String traderId, String stock);

    List<Portfolio> findByTraderId(String traderId);
}