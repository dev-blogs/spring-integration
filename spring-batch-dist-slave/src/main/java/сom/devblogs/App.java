package сom.devblogs;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static String id;
	
	public static void main(String[] args) {
		id = args[0];
		new ClassPathXmlApplicationContext("classpath:context.xml");
	}
}