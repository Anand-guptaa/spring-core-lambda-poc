package com.springcore.lambda.poc.handler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.springcore.lambda.poc.apihandler.DemoAPIHandler;
import com.springcore.lambda.poc.handler.config.AppConfig;

public class SpringAPIHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger Log = LogManager.getLogger(SpringAPIHandler.class);
    private ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
    
    
    @Override
    public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent request, Context context) {
		Log.info("Lambda function started");
    	APIGatewayProxyResponseEvent response = new APIGatewayProxyResponseEvent();
        try {
            DemoAPIHandler handler = applicationContext.getBean(DemoAPIHandler.class);
            response.setStatusCode(200);
            response.setBody(objectMapper.writeValueAsString(handler.requestHanlder()));
        } catch (Exception e) {
            response.setStatusCode(500);
            response.setBody("Internal Server Error");
            Log.error(e);
        }
		Log.info("Lambda function ended");
        return response;
    }
}

