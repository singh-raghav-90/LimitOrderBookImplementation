package com.limit.order.mainentry;

import java.io.File;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

import com.limit.order.model.LimitOrder;
import com.limit.order.util.LimitOrderFileWatcher;

public class StartExecutingLimitOrder {
	public static TreeMap<BigDecimal, TreeMap<LocalDateTime, List<LimitOrder>>> timePriceBuyMap = new TreeMap<BigDecimal, TreeMap<LocalDateTime, List<LimitOrder>>>(
			Collections.reverseOrder());
	public static TreeMap<BigDecimal, TreeMap<LocalDateTime, List<LimitOrder>>> timePriceSellMap = new TreeMap<BigDecimal, TreeMap<LocalDateTime, List<LimitOrder>>>();
	public static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	static {

		synchronized (timePriceBuyMap) {
			timePriceBuyMap = new TreeMap<BigDecimal, TreeMap<LocalDateTime, List<LimitOrder>>>(
					Collections.reverseOrder());

		}
		synchronized (timePriceSellMap) {
			timePriceSellMap = new TreeMap<BigDecimal, TreeMap<LocalDateTime, List<LimitOrder>>>();
		}
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String orderFileDirectory = "C:\\LimitOrderBooking\\";
		 File directory = new File(orderFileDirectory);
		    if (!directory.exists()){
		    	 directory.mkdir();
		    }
		try {
			new LimitOrderFileWatcher().processFiles(orderFileDirectory);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
