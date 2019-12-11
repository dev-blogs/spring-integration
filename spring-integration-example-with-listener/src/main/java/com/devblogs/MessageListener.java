package com.devblogs;

public class MessageListener {
	public String launch(String message) {
		System.out.println("echo: " + message);
		return message;
	}
}