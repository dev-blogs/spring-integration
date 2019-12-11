package com.devblogs.sender;

import java.net.URI;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.broker.BrokerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.connection.CachingConnectionFactory;
import org.springframework.jms.core.JmsTemplate;

@Configuration
public class SenderConfig {
	@Value("tcp://localhost:61616")
	private String brokerUrl;

	@Bean
	public ActiveMQConnectionFactory senderActiveMQConnectionFactory() {
		ActiveMQConnectionFactory activeMQConnectionFactory = new ActiveMQConnectionFactory();
		activeMQConnectionFactory.setBrokerURL(brokerUrl);
		return activeMQConnectionFactory;
	}

	@Bean
	public void broker() throws Exception {
		BrokerFactory.createBroker(new URI("broker:(tcp://localhost:61616)")).start();
	}
	
	@Bean
	public CachingConnectionFactory cachingConnectionFactory() {
		return new CachingConnectionFactory(senderActiveMQConnectionFactory());
	}

	@Bean
	public JmsTemplate jmsTemplate() {
		return new JmsTemplate(cachingConnectionFactory());
	}

	@Bean
	public Sender sender() {
		return new Sender();
	}
}