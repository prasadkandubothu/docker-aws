package com.prasad;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyRequestEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayProxyResponseEvent;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.Collections;
import java.util.Map;

public class LambdaRequestHandler implements RequestHandler<APIGatewayProxyRequestEvent, APIGatewayProxyResponseEvent> {

        public APIGatewayProxyResponseEvent handleRequest(APIGatewayProxyRequestEvent apiGatewayProxyRequestEvent, Context context){
                APIGatewayProxyResponseEvent apiGatewayProxyResponseEvent = new APIGatewayProxyResponseEvent();
                try {
                        ObjectMapper objectMapper=new ObjectMapper();
                        String requestString = apiGatewayProxyRequestEvent.getBody();
                        System.out.println("Input Data : "+requestString);

                        if(requestString != null){
                                Map map=objectMapper.readValue(requestString, Map.class);
                                if(!map.isEmpty()){
                                        generateResponse(apiGatewayProxyResponseEvent, objectMapper.writeValueAsString(map));
                                }else{
                                        System.err.println("Input is empty Json");
                                }
                        }else{
                                System.err.println("Input is null");
                        }
                } catch (Exception e) {
                        e.printStackTrace();
                }
                return apiGatewayProxyResponseEvent;
        }

        private void generateResponse(APIGatewayProxyResponseEvent apiGatewayProxyResponseEvent, String requestMessage) {
                apiGatewayProxyResponseEvent.setHeaders(Collections.singletonMap("timeStamp", String.valueOf(System.currentTimeMillis())));
                apiGatewayProxyResponseEvent.setStatusCode(200);
                apiGatewayProxyResponseEvent.setBody(requestMessage);
        }
}


