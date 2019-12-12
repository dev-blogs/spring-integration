package сom.devblogs.receiver;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.support.converter.MessageConverter;

public class MessageReceiver implements MessageListener {

	static final Logger LOG = LoggerFactory.getLogger(MessageReceiver.class);

	private MessageConverter messageConverter;

	public void setMessageConverter(MessageConverter messageConverter) {
		this.messageConverter = messageConverter;
	}

	@Override
	public void onMessage(Message message) {
		try {
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
			String response = (String) messageConverter.fromMessage(message);
			LOG.info("Application : order response received : {}", response);
			System.out.println("echo: " + response);
			LOG.info("+++++++++++++++++++++++++++++++++++++++++++++++++++++");
		} catch (JMSException e) {
			e.printStackTrace();
		}
	}
}