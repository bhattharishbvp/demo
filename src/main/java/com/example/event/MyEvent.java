package com.example.event;

import org.springframework.stereotype.Component;

@Component
public class MyEvent {

	private String eventInitiator;
	
	public MyEvent(String eventInitiator) {
		this.eventInitiator = eventInitiator;
	}
	
	public String getEventInitiator() {
		return eventInitiator;
	}


	public void setEventInitiator(String eventInitiator) {
		this.eventInitiator = eventInitiator;
	}
	
}
