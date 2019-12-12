package сom.devblogs;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) {
		new ClassPathXmlApplicationContext("classpath:context.xml");
	}
}