package com.devblogs;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.Message;
import org.springframework.integration.support.MessageBuilder;
import com.devblogs.sender.Gateway;

public class App {
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:context.xml");
		
		Message<String> msg = MessageBuilder.withPayload("test").build();
		
		Gateway gateway = ctx.getBean("product-gateway", Gateway.class);
		gateway.launch("test message from spring integration");
	}
}