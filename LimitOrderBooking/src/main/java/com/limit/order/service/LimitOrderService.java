package com.limit.order.service;

import java.util.function.Predicate;

import com.limit.order.Impl.LimitOrderBookImpl;
import com.limit.order.exception.LimitOrderBookException;
import com.limit.order.model.LimitOrder;

public class LimitOrderService {

	
	public void  addNewBuyOrder(LimitOrder order) {
		// TODO Auto-generated method stub
		try {
			
			new LimitOrderBookImpl().addNewBuyOrder(order);
		}catch(LimitOrderBookException e) {
			System.out.println("Error occured while adding a buy order : "+e.getMessage());
		}
	}

	
	public void addNewSellOrder(LimitOrder order) {
		// TODO Auto-generated method stub
		try {
			new LimitOrderBookImpl().addNewSellOrder(order);
		}catch(LimitOrderBookException e) {
			System.out.println("Error occured while adding a sell order : "+e.getMessage());
		}
	}

	
	public void cancelOrder(LimitOrder order) {
		// TODO Auto-generated method stub
		try {
			System.out.println();
			new LimitOrderBookImpl().cancelOrder(order);
		}catch(LimitOrderBookException e) {
			System.out.println("Error occured while cancelling an order : "+e.getMessage());
		}
	}

	
	public boolean rejectOrder(LimitOrder order) {
		// TODO Auto-generated method stub
		Predicate<Long> quantity = i -> i > 100;
		return quantity.test(order.getQuantity());
		
	}

	
	public void displayLimitOrderBook() {
		// TODO Auto-generated method stub
		try {
			System.out.println();
			new LimitOrderBookImpl().displayLimitOrderBook();;
		}catch(LimitOrderBookException e) {
			System.out.println("Error occured while displaying the summary of the order : "+e.getMessage());
		}
	}

	
}
