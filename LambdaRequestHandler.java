package com.prasad;


import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import com.amazonaws.services.lambda.runtime.RequestHandler;

import java.util.Map;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Hello world!
 */
public class LambdaRequestHandler implements RequestHandler<Map<String,String>, String> {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        public String handleRequest(Map<String,String> input, Context context){
                LambdaLogger logger = context.getLogger();
                String response = new String("200 OK");
                // log execution details
                logger.log("ENVIRONMENT VARIABLES: " + gson.toJson(System.getenv()));
                logger.log("CONTEXT: " + gson.toJson(context));
                // process event
                logger.log("EVENT: " + gson.toJson(input));
                logger.log("EVENT TYPE: " + input.getClass().toString());
                context.getLogger().log("Input: "+input);
                return gson.toJson(input);
        }
}


