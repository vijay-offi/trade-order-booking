package com.fynxt.trade_order_booking.dto;

import com.fynxt.trade_order_booking.entity.Side;
import lombok.Data;

@Data
public class OrderRequest {

    private String traderId;

    public String getTraderId() {
		return traderId;
	}

	public void setTraderId(String traderId) {
		this.traderId = traderId;
	}

	public String getStock() {
		return stock;
	}

	public void setStock(String stock) {
		this.stock = stock;
	}

	public String getSector() {
		return sector;
	}

	public void setSector(String sector) {
		this.sector = sector;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public Side getSide() {
		return side;
	}

	public void setSide(Side side) {
		this.side = side;
	}

	private String stock;

    private String sector;

    private int quantity;

    private Side side;
}
