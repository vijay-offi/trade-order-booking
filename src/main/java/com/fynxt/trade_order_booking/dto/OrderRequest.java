package com.fynxt.trade_order_booking.dto;

import com.fynxt.trade_order_booking.entity.Side;

import lombok.Data;

@Data
public class OrderRequest {

    private String traderId;

    private String stock;

    private String sector;

    private int quantity;

    private Side side;
}
