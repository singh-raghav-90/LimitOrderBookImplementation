package com.limit.order.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.opencsv.bean.CsvBindByPosition;

public class LimitOrder {
	
	@CsvBindByPosition(position = 0)
	public String orderID;
	
	@CsvBindByPosition(position = 1)
	public String direction;
	 
	@CsvBindByPosition(position = 2)
	public String time;	
	
	@CsvBindByPosition(position = 3) 
	public long quantity;
	
	@CsvBindByPosition(position = 4)
	public BigDecimal limitPrice;
	
	@CsvBindByPosition(position = 5)
	public String orderType;
	@CsvBindByPosition(position = 6)
	public String ticker;
	
	public LimitOrder() {
		
	}

	public LimitOrder(String orderID, String direction, long quantity, String time, BigDecimal limitPrice, String ticker) {
		super();
		this.orderID = orderID;
		this.direction = direction;
		this.quantity = quantity;
		this.time = time;
		this.limitPrice = limitPrice;
		this.ticker = ticker;
	}

	public String getOrderID() {
		return orderID;
	}

	public void setOrderID(String orderID) {
		this.orderID = orderID;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}

	public long getQuantity() {
		return quantity;
	}

	public void setQuantity(long quantity) {
		this.quantity = quantity;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public BigDecimal getLimitPrice() {
		return limitPrice;
	}

	public void setLimitPrice(BigDecimal limitPrice) {
		this.limitPrice = limitPrice;
	}

	
	public String getTicker() {
		return ticker;
	}

	public void setTicker(String ticker) {
		this.ticker = ticker;
	}

	
	
	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	@Override
	public String toString() {
		return "LimitOrder [orderID=" + orderID + ", direction=" + direction + ", time=" + time + ", quantity="
				+ quantity + ", limitPrice=" + limitPrice + ", orderType=" + orderType + ", ticker=" + ticker + "]";
	}
}
