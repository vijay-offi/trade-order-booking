package com.fynxt.trade_order_booking.dto;

import lombok.Data;

@Data
public class AddPortfolioRequest {

    private String traderId;

    private String stock;

    private String sector;

    private int quantity;
}
