package com.example.processor;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.file.FlatFileParseException;
import com.example.model.Product;

public class Processor implements ItemProcessor<Product, Product> {
	private static int count = 0;
	
	@Override
	public Product process(Product item) throws Exception {
		/*count++;
		if (count == 12 || count == 25) {
			throw new FlatFileParseException("test", "test");
		}*/
		return item;
	}
}