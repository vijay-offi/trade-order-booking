package com.fynxt.trade_order_booking.service;


import java.time.LocalDateTime;
import java.util.HashMap;

import org.hibernate.mapping.List;
import org.hibernate.mapping.Map;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fynxt.trade_order_booking.dto.AddPortfolioRequest;
import com.fynxt.trade_order_booking.dto.OrderRequest;
import com.fynxt.trade_order_booking.dto.PortfolioResponse;
import com.fynxt.trade_order_booking.entity.Order;
import com.fynxt.trade_order_booking.entity.Portfolio;
import com.fynxt.trade_order_booking.entity.Side;
import com.fynxt.trade_order_booking.repository.OrderRepository;
import com.fynxt.trade_order_booking.repository.PortfolioRepository;

import ch.qos.logback.core.status.Status;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final PortfolioRepository portfolioRepository;

    @Transactional
    public Order placeOrder(OrderRequest request) {

        long pendingCount = orderRepository
                .countByTraderIdAndStatus(request.getTraderId(), Status.PENDING);

        if (pendingCount >= 3) {
            throw new RuntimeException("Maximum 3 pending orders allowed");
        }

        if (request.getSide() == Side.SELL) {

            Portfolio portfolio = portfolioRepository
                    .findByTraderIdAndStock(request.getTraderId(), request.getStock())
                    .orElseThrow(() ->
                            new RuntimeException("No holdings found"));

            if (portfolio.getQuantity() < request.getQuantity()) {
                throw new RuntimeException("Not enough shares to sell");
            }
        }

        Order order = new Order();
        order.setTraderId(request.getTraderId());
        order.setStock(request.getStock());
        order.setSector(request.getSector());
        order.setQuantity(request.getQuantity());
        order.setSide(request.getSide());
        order.setStatus(Status.PENDING);
        order.setCreatedAt(LocalDateTime.now());

        return orderRepository.save(order);
    }

    @Transactional
    public String fillOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        if (order.getStatus() != Status.PENDING) {
            throw new RuntimeException("Only PENDING orders can be filled");
        }

        Portfolio portfolio = portfolioRepository
                .findByTraderIdAndStock(order.getTraderId(), order.getStock())
                .orElse(new Portfolio());

        portfolio.setTraderId(order.getTraderId());
        portfolio.setStock(order.getStock());
        portfolio.setSector(order.getSector());

        if (order.getSide() == Side.BUY) {
            portfolio.setQuantity(
                    portfolio.getQuantity() + order.getQuantity()
            );
        } else {
            portfolio.setQuantity(
                    portfolio.getQuantity() - order.getQuantity()
            );
        }

        portfolioRepository.save(portfolio);

        order.setStatus(Status.FILLED);
        orderRepository.save(order);

        return "Order filled successfully";
    }

    @Transactional
    public String cancelOrder(Long orderId) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        if (order.getStatus() != Status.PENDING) {
            throw new RuntimeException("Only PENDING orders can be cancelled");
        }

        order.setStatus(Status.CANCELLED);

        orderRepository.save(order);

        return "Order cancelled successfully";
    }

    public PortfolioResponse getPortfolio(String traderId) {

        List<Portfolio> portfolios =
                portfolioRepository.findByTraderId(traderId);

        Map<String, Integer> positions = new HashMap<>();
        Map<String, Integer> sectorBreakdown = new HashMap<>();

        for (Portfolio portfolio : portfolios) {

            positions.put(
                    portfolio.getStock(),
                    portfolio.getQuantity()
            );

            sectorBreakdown.put(
                    portfolio.getSector(),
                    sectorBreakdown.getOrDefault(
                            portfolio.getSector(), 0
                    ) + portfolio.getQuantity()
            );
        }

        return new PortfolioResponse(
                traderId,
                positions,
                sectorBreakdown
        );
    }

    @Transactional
    public String addToPortfolio(AddPortfolioRequest request) {

        Portfolio portfolio = portfolioRepository
                .findByTraderIdAndStock(
                        request.getTraderId(),
                        request.getStock()
                )
                .orElse(new Portfolio());

        portfolio.setTraderId(request.getTraderId());
        portfolio.setStock(request.getStock());
        portfolio.setSector(request.getSector());

        portfolio.setQuantity(
                portfolio.getQuantity() + request.getQuantity()
        );

        portfolioRepository.save(portfolio);

        return "Portfolio updated successfully";
    }
}
