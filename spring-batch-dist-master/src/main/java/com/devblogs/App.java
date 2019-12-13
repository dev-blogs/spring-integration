package com.devblogs;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
	public static void main(String[] args) throws Exception {
		ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring/context.xml");
		
		JobLauncher jobLauncher = ctx.getBean("jobLauncher", JobLauncher.class);
		Job job = ctx.getBean("importJob", Job.class);
		
		jobLauncher.run(job, new JobParametersBuilder().addLong("timestamp", System.currentTimeMillis()).toJobParameters());
		
		//Message<String> msg = MessageBuilder.withPayload("test").build();
		//Gateway gateway = ctx.getBean("product-gateway", Gateway.class);
		//gateway.launch("test message from spring integration");
	}
}