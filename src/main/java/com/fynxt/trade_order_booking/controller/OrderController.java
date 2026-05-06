package com.fynxt.trade_order_booking.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fynxt.trade_order_booking.dto.AddPortfolioRequest;
import com.fynxt.trade_order_booking.dto.OrderRequest;
import com.fynxt.trade_order_booking.dto.PortfolioResponse;
import com.fynxt.trade_order_booking.entity.Order;
import com.fynxt.trade_order_booking.service.OrderService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/orders")
    public Order placeOrder(@RequestBody OrderRequest request) {
        return orderService.placeOrder(request);
    }

    @PostMapping("/orders/{id}/fill")
    public String fillOrder(@PathVariable Long id) {
        return orderService.fillOrder(id);
    }

    @PostMapping("/orders/{id}/cancel")
    public String cancelOrder(@PathVariable Long id) {
        return orderService.cancelOrder(id);
    }

    @GetMapping("/portfolio/{traderId}")
    public PortfolioResponse getPortfolio(
            @PathVariable String traderId
    ) {
        return orderService.getPortfolio(traderId);
    }

    @PostMapping("/portfolio")
    public String addToPortfolio(
            @RequestBody AddPortfolioRequest request
    ) {
        return orderService.addToPortfolio(request);
    }
}
