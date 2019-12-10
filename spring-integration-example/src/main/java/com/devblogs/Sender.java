package com.devblogs;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.integration.Message;
import org.springframework.integration.MessageChannel;
import org.springframework.integration.support.MessageBuilder;

public class Sender {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:context.xml");
		
		Message<String> msg = MessageBuilder.withPayload("test").build();
		
		MessageChannel jobRequestChannel = ctx.getBean("job-request", MessageChannel.class);
		jobRequestChannel.send(msg);
	}
}