package com.springcore.lambda.poc.apihandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

@Component
public class DemoAPIHandler {

	private static final Logger Log = LogManager.getLogger(DemoAPIHandler.class);
	public String requestHanlder() {
		Log.info("Lambda function started");
		String response = "success";
		Log.info("Lambda function ended");
		return response;
	}
}
