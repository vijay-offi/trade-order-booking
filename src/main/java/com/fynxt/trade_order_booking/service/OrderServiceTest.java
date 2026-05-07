package com.fynxt.trade_order_booking.service;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.fynxt.trade_order_booking.dto.OrderRequest;
import com.fynxt.trade_order_booking.entity.Order;
import com.fynxt.trade_order_booking.entity.Side;
import com.fynxt.trade_order_booking.entity.Status;
import com.fynxt.trade_order_booking.repository.OrderRepository;
import com.fynxt.trade_order_booking.repository.PortfolioRepository;

@ExtendWith(MockitoExtension.class)
class OrderServiceTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private PortfolioRepository portfolioRepository;

    @InjectMocks
    private OrderService orderService;

    @Test
    void shouldPlaceBuyOrderSuccessfully() {

        OrderRequest request = new OrderRequest();
        request.setTraderId("T001");
        request.setStock("AAPL");
        request.setSector("TECH");
        request.setQuantity(10);
        request.setSide(Side.BUY);

        when(orderRepository.countByTraderIdAndStatus(
                "T001",
                Status.PENDING
        )).thenReturn(0L);

        when(orderRepository.save(any(Order.class)))
                .thenAnswer(invocation -> invocation.getArgument(0));

        Order result = orderService.placeOrder(request);

        assertNotNull(result);
        assertEquals(Status.PENDING, result.getStatus());
    }
    
    @Test
    void shouldRejectWhenPendingOrdersExceedLimit() {

        OrderRequest request = new OrderRequest();
        request.setTraderId("T001");
        request.setSide(Side.BUY);

        when(orderRepository.countByTraderIdAndStatus(
                "T001",
                Status.PENDING
        )).thenReturn(3L);

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> orderService.placeOrder(request)
        );

        assertEquals(
                "Maximum 3 pending orders allowed",
                ex.getMessage()
        );
    }
    
    @Test
    void shouldRejectSellWhenNoHoldings() {

        OrderRequest request = new OrderRequest();
        request.setTraderId("T001");
        request.setStock("AAPL");
        request.setQuantity(10);
        request.setSide(Side.SELL);

        when(orderRepository.countByTraderIdAndStatus(
                "T001",
                Status.PENDING
        )).thenReturn(0L);

        when(portfolioRepository.findByTraderIdAndStock(
                "T001",
                "AAPL"
        )).thenReturn(java.util.Optional.empty());

        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> orderService.placeOrder(request)
        );

        assertEquals("No holdings found", ex.getMessage());
    }
}
