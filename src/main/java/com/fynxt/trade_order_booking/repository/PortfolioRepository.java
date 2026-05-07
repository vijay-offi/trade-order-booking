package com.fynxt.trade_order_booking.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.fynxt.trade_order_booking.entity.Portfolio;

public interface PortfolioRepository extends JpaRepository<Portfolio, Long> {

    Optional<Portfolio> findByTraderIdAndStock(String traderId, String stock);

    List<Portfolio> findByTraderId(String traderId);
}