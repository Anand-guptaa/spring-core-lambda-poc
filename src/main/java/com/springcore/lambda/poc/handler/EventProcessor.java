package com.springcore.lambda.poc.handler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.ScheduledEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springcore.lambda.poc.eventhandler.ScheduleEventHandler;
import com.springcore.lambda.poc.handler.config.AppConfig;


public class EventProcessor implements RequestHandler<ScheduledEvent, String> {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger Log = LogManager.getLogger(EventProcessor.class);
    private ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    
    @Override
    public String handleRequest(ScheduledEvent event, Context context) {
		Log.info("scheduler function started");
        try {
        	ScheduleEventHandler handler = applicationContext.getBean(ScheduleEventHandler.class);
        	Log.info("response: {}", objectMapper.writeValueAsString(handler.eventHanlder()));
        } catch (Exception e) {
        	Log.error("Internal Server Error");
        	Log.error(e);
        }
		Log.info("scheduler function started");
		return "Event processed successfully";
    }
}

