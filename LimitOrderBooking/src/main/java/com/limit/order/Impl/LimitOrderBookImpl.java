package com.limit.order.Impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;
import com.limit.order.IAction.OrderAction;
import com.limit.order.exception.LimitOrderBookException;
import com.limit.order.mainentry.StartExecutingLimitOrder;
import com.limit.order.model.LimitOrder;

public class LimitOrderBookImpl implements OrderAction {

	@Override
	public boolean addNewBuyOrder(LimitOrder order) throws LimitOrderBookException {
		// TODO Auto-generated method stub

		boolean addStatus = true;
		LocalDateTime dateTime = null;
		try {

			BigDecimal price = order.getLimitPrice();
			if (StartExecutingLimitOrder.timePriceBuyMap.get(price) == null) {
				List<LimitOrder> orderList = new ArrayList<LimitOrder>();
				orderList.add(order);
				TreeMap<LocalDateTime, List<LimitOrder>> timeDataSort = new TreeMap<LocalDateTime, List<LimitOrder>>();
				dateTime = LocalDateTime.parse(order.getTime(), StartExecutingLimitOrder.formatter);

				timeDataSort.put(dateTime, orderList);
				StartExecutingLimitOrder.timePriceBuyMap.put(price, timeDataSort);

			} else {
				TreeMap<LocalDateTime, List<LimitOrder>> timeDataSort = StartExecutingLimitOrder.timePriceBuyMap
						.get(price);
				dateTime = LocalDateTime.parse(order.getTime(), StartExecutingLimitOrder.formatter);
				if (timeDataSort.containsKey(dateTime)) {
					timeDataSort.get(dateTime).add(order);
				} else {
					List<LimitOrder> orderList = new ArrayList<LimitOrder>();

					orderList.add(order);
					timeDataSort.put(dateTime, orderList);
					StartExecutingLimitOrder.timePriceBuyMap.put(price, timeDataSort);
				}
			}

		} catch (Exception e) {
			throw new LimitOrderBookException("Error occured while adding a new buy order : " + e.getMessage());
		}
		return addStatus;
	}

	@Override
	public boolean addNewSellOrder(LimitOrder order) throws LimitOrderBookException {
		// TODO Auto-generated method stub

		boolean addStatus = true;
		LocalDateTime dateTime = null;
		try {

			BigDecimal price = order.getLimitPrice();
			if (StartExecutingLimitOrder.timePriceSellMap.get(price) == null) {
				List<LimitOrder> orderList = new ArrayList<LimitOrder>();
				orderList.add(order);
				TreeMap<LocalDateTime, List<LimitOrder>> timeDataSort = new TreeMap<LocalDateTime, List<LimitOrder>>();
				dateTime = LocalDateTime.parse(order.getTime(), StartExecutingLimitOrder.formatter);
				timeDataSort.put(dateTime, orderList);
				StartExecutingLimitOrder.timePriceSellMap.put(price, timeDataSort);

			} else {
				TreeMap<LocalDateTime, List<LimitOrder>> timeDataSort = StartExecutingLimitOrder.timePriceSellMap
						.get(price);
				dateTime = LocalDateTime.parse(order.getTime(), StartExecutingLimitOrder.formatter);
				if (timeDataSort.containsKey(dateTime)) {
					timeDataSort.get(dateTime).add(order);
				} else {
					List<LimitOrder> orderList = new ArrayList<LimitOrder>();
					orderList.add(order);
					dateTime = LocalDateTime.parse(order.getTime(), StartExecutingLimitOrder.formatter);
					timeDataSort.put(dateTime, orderList);
					StartExecutingLimitOrder.timePriceSellMap.put(price, timeDataSort);
				}
			}

		} catch (Exception e) {
			throw new LimitOrderBookException("Error occured while adding a new sell order : " + e.getMessage());
		}
		return addStatus;
	}

	@Override
	public boolean cancelOrder(LimitOrder order) throws LimitOrderBookException {
		// TODO Auto-generated method stub

		LocalDateTime dateTime = null;
		dateTime = LocalDateTime.parse(order.getTime(), StartExecutingLimitOrder.formatter);
		if (order.getDirection().equalsIgnoreCase("BUY")) {

			if (StartExecutingLimitOrder.timePriceBuyMap.get(order.getLimitPrice()) == null) {
				throw new LimitOrderBookException("Error occured while cancelling an order : ");
			} else {
				TreeMap<LocalDateTime, List<LimitOrder>> timeDataSort = StartExecutingLimitOrder.timePriceBuyMap
						.get(order.getLimitPrice());
				if (timeDataSort.get(dateTime) != null) {

					for (LimitOrder clord : timeDataSort.get(dateTime)) {
						if (clord.getOrderID().equalsIgnoreCase(order.getOrderID())) {
							timeDataSort.get(dateTime).remove(clord);
						}
					}
					StartExecutingLimitOrder.timePriceBuyMap.put(order.getLimitPrice(), timeDataSort);
				}

			}
		} else {

			if (StartExecutingLimitOrder.timePriceSellMap.get(order.getLimitPrice()) == null) {

			} else {
				TreeMap<LocalDateTime, List<LimitOrder>> timeDataSort = StartExecutingLimitOrder.timePriceSellMap
						.get(order.getLimitPrice());
				if (timeDataSort.get(dateTime) != null) {

					for (LimitOrder clord : timeDataSort.get(dateTime)) {
						if (clord.getOrderID().equalsIgnoreCase(order.getOrderID())) {
							timeDataSort.get(dateTime).remove(clord);
						}
					}
					StartExecutingLimitOrder.timePriceSellMap.put(order.getLimitPrice(), timeDataSort);
				}

			}

		}

		return false;
	}

	@Override
	public boolean rejectOrder(LimitOrder order) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void displayLimitOrderBook() throws LimitOrderBookException {
		// TODO Auto-generated method stub

		System.out.println("======================BUY ORDER============================");
		try {

			StartExecutingLimitOrder.timePriceBuyMap.forEach((k, v) -> {
				v.forEach((x, y) -> {
					y.forEach(order -> System.out.println("Limit price = " + order.getLimitPrice() + " ,  time = "
							+ order.getTime() + " , Order ID = " + order.getOrderID()));
				});
			});
			System.out.println("======================SELL ORDER============================");
			StartExecutingLimitOrder.timePriceSellMap.forEach((k, v) -> {
				v.forEach((x, y) -> {
					y.forEach(order -> System.out.println("Limit price = " + order.getLimitPrice() + " ,  time = "
							+ order.getTime() + " , Order ID = " + order.getOrderID()));
				});
			});
			if(Optional.of(StartExecutingLimitOrder.timePriceBuyMap.firstKey()).isPresent() || Optional.of(StartExecutingLimitOrder.timePriceSellMap.firstKey()).isPresent())
			System.out.println("Best Bid Price = "+StartExecutingLimitOrder.timePriceBuyMap.firstKey() + " , Best Ask Price = "+StartExecutingLimitOrder.timePriceSellMap.firstKey() );
		} catch (Exception e) {
			throw new LimitOrderBookException("Error occured while displaying the summary of the limit order book...");
		}

	}

}
