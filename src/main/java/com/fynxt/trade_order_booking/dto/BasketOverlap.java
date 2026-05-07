package com.fynxt.trade_order_booking.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class BasketOverlap {
	
    public BasketOverlap(String basket, String overlap) {
        this.basket = basket;
        this.overlap = overlap;
    }

    public String getBasket() {
		return basket;
	}

	public void setBasket(String basket) {
		this.basket = basket;
	}

	public String getOverlap() {
		return overlap;
	}

	public void setOverlap(String overlap) {
		this.overlap = overlap;
	}

	private String basket;

    private String overlap;
}