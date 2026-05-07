package com.fynxt.trade_order_booking.dto;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PortfolioResponse {
	
    public PortfolioResponse(
            String traderId,
            Map<String, Integer> positions,
            Map<String, Integer> sectorBreakdown
    ) {
        this.traderId = traderId;
        this.positions = positions;
        this.sectorBreakdown = sectorBreakdown;
    }

    public String getTraderId() {
		return traderId;
	}

	public void setTraderId(String traderId) {
		this.traderId = traderId;
	}

	public Map<String, Integer> getPositions() {
		return positions;
	}

	public void setPositions(Map<String, Integer> positions) {
		this.positions = positions;
	}

	public Map<String, Integer> getSectorBreakdown() {
		return sectorBreakdown;
	}

	public void setSectorBreakdown(Map<String, Integer> sectorBreakdown) {
		this.sectorBreakdown = sectorBreakdown;
	}

	private String traderId;

    private Map<String, Integer> positions;

    private Map<String, Integer> sectorBreakdown;
}
