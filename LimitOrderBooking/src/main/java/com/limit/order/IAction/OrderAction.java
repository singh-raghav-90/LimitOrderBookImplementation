package com.limit.order.IAction;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.TreeMap;

import com.limit.order.exception.LimitOrderBookException;
import com.limit.order.model.LimitOrder;

public interface OrderAction {

	public boolean addNewBuyOrder(LimitOrder order) throws LimitOrderBookException;
	public boolean addNewSellOrder(LimitOrder order) throws LimitOrderBookException;
	public boolean cancelOrder(LimitOrder order) throws LimitOrderBookException;
	public boolean rejectOrder(LimitOrder order) throws LimitOrderBookException;
	public void displayLimitOrderBook() throws LimitOrderBookException;
}
