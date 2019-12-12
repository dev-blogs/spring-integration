package com.devblogs.sender;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

public class Sender {
	private static final Logger LOGGER = LoggerFactory.getLogger(Sender.class);
	
	private JmsTemplate jmsTemplate;
	
	public void setJmsTemplate(JmsTemplate jmsTemplate) {
		this.jmsTemplate = jmsTemplate;
	}

	public void send(String message) {
		jmsTemplate.convertAndSend(message);
	}
}