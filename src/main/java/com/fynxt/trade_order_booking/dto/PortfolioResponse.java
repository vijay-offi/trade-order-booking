package com.fynxt.trade_order_booking.dto;

@Data
@AllArgsConstructor
public class PortfolioResponse {

    private String traderId;

    private Map<String, Integer> positions;

    private Map<String, Integer> sectorBreakdown;
}
