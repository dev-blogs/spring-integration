package com.devblogs.batch.reader;

import org.springframework.batch.item.ExecutionContext;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemStream;
import org.springframework.batch.item.ItemStreamException;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import com.devblogs.model.Product;

public class WrappedItemReader implements ItemReader<Product>, ItemStream {
	private ItemReader<Product> delegateReader;
	
	public void setDelegateReader(ItemReader<Product> delegateReader) {
		this.delegateReader = delegateReader;
	}
	
	@Override
	public Product read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
		return delegateReader.read();
	}

	@Override
	public void open(ExecutionContext executionContext) throws ItemStreamException {
		if (this.delegateReader instanceof ItemStream) {
			((ItemStream) this.delegateReader).open(executionContext);
		}
	}

	@Override
	public void update(ExecutionContext executionContext) throws ItemStreamException {
		if (this.delegateReader instanceof ItemStream) {
			((ItemStream) this.delegateReader).update(executionContext);
		}
	}

	@Override
	public void close() throws ItemStreamException {
		if (this.delegateReader instanceof ItemStream) {
			((ItemStream) this.delegateReader).close();
		}
	}
}