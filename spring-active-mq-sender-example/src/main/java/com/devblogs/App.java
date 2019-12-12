package com.devblogs;

import java.net.URI;
import org.apache.activemq.broker.BrokerFactory;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.devblogs.sender.Sender;

public class App {
	public static void main(String[] args) throws Exception {
		//BrokerFactory.createBroker(new URI("broker:(tcp://localhost:61616)")).start();
		
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:context.xml");
		Sender sender = ctx.getBean("sender", Sender.class);
		
		sender.send("test message");
	}
}