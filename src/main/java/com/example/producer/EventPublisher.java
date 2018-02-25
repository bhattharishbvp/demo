package com.example.producer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;

import com.example.event.MyEvent;

public class EventPublisher {

	@Autowired
	ApplicationEventPublisher publisher;
	
	
	public void publish(MyEvent event) {
		publisher.publishEvent(event);
	}
}
