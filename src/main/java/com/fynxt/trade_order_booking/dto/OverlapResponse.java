package com.fynxt.trade_order_booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class OverlapResponse {
	
    public OverlapResponse(
            List<BasketOverlap> overlaps,
            String dominantBasket,
            String riskFlag
    ) {
        this.overlaps = overlaps;
        this.dominantBasket = dominantBasket;
        this.riskFlag = riskFlag;
    }

    public List<BasketOverlap> getOverlaps() {
		return overlaps;
	}

	public void setOverlaps(List<BasketOverlap> overlaps) {
		this.overlaps = overlaps;
	}

	public String getDominantBasket() {
		return dominantBasket;
	}

	public void setDominantBasket(String dominantBasket) {
		this.dominantBasket = dominantBasket;
	}

	public String getRiskFlag() {
		return riskFlag;
	}

	public void setRiskFlag(String riskFlag) {
		this.riskFlag = riskFlag;
	}

	private List<BasketOverlap> overlaps;

    private String dominantBasket;

    private String riskFlag;
}
