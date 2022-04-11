package com.limit.order.util;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;

import com.limit.order.model.LimitOrder;
import com.opencsv.bean.CsvToBeanBuilder;

public class LimitOrderUtil {

	public static List<LimitOrder> readLimitOrderCSV(File filepath) throws IOException {
		List<LimitOrder> orders = null;
		FileReader reader = null;

		try {
			reader = new FileReader(filepath);
			if (reader != null) {
				if (filepath.exists()) {
					orders = new CsvToBeanBuilder<LimitOrder>(reader).withType(LimitOrder.class).build().parse();

					if (Optional.of(orders).isPresent())
						orders.forEach(order -> System.out.println(order));
				}else {
					
				}
			}

		} catch (FileNotFoundException e) {
			System.out.println("Order file to be processed is not found.." + e.getMessage());
		} catch (Exception e) {
			System.out.println("Error occured while reading the order files.." + e.getMessage());
		} finally {
			reader.close();
		}
		return orders;

	}

}
