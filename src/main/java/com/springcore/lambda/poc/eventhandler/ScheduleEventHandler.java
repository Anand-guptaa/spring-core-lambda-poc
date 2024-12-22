package com.springcore.lambda.poc.eventhandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class ScheduleEventHandler {

	private static final Logger Log = LogManager.getLogger(ScheduleEventHandler.class);
	
	public String eventHanlder() {
		Log.info("ScheduleEventHandler function started");
		String response = "success";
		Log.info("ScheduleEventHandler function ended");
		return response;
	}
}
