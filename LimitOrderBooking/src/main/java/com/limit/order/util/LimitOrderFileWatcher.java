package com.limit.order.util;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.limit.order.model.LimitOrder;
import com.limit.order.service.LimitOrderService;

public class LimitOrderFileWatcher {

	private LimitOrderService orderService = null;
	private List<LimitOrder> listOfLimitOrder = null;
	private LimitOrderUtil util = null;

	public LimitOrderFileWatcher() {
		this.orderService = new LimitOrderService();
		this.listOfLimitOrder = new ArrayList<LimitOrder>();
		this.util = new LimitOrderUtil();
	}

	public void processFiles(String dir) {
		File folder = new File(dir);
		while (true) {

			File[] listOfFiles = folder.listFiles();

			for (File file : listOfFiles) {
				if (file.isFile()) {
					System.out.println(file.getName());
					try {
						this.listOfLimitOrder = this.util.readLimitOrderCSV(file);

						Optional.of(this.listOfLimitOrder).orElseGet(Collections::emptyList).stream()
								.forEach(limitOrder -> {

									Optional<LimitOrder> checkOrder = Optional.of(limitOrder);

									if (checkOrder.isPresent()) {
										String orderType = limitOrder.getOrderType();
										switch (orderType) {
										case "new" -> {
											boolean rejectOrder = this.orderService.rejectOrder(limitOrder);
											if (rejectOrder) {

											} else {
												System.out.println("Order rejected as the lot size is less than 100");
												break;
											}
											if (limitOrder.getDirection().equalsIgnoreCase("BUY")) {
												this.orderService.addNewBuyOrder(limitOrder);
											} else {
												this.orderService.addNewSellOrder(limitOrder);
											}
										}

										case "cancel" -> this.orderService.cancelOrder(limitOrder);

										}
										;

									}
								});

					} catch (Exception e) {

						System.out.println("Error occured while processing the order : " + e.getMessage());

					} finally {

						if (file.exists()) {
							file.delete();

						}

						this.orderService.displayLimitOrderBook();
					}

				}
			}
			try {
				Thread.sleep(3000);

			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
