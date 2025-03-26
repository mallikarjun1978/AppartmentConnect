package com.example.demo.customactuatorendpoint;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
@Endpoint(id = "appendpoints")
public class CustomActuatorEndpoint {

    @ReadOperation
    public Map<String, String> customInfo() {
    	Map<String, String> endPoints = new HashMap<>();
    	endPoints.put("index page", "http://localhost:8090/index");
    	endPoints.put("about page", "http://localhost:8090/about");
    	endPoints.put("contact page", "http://localhost:8090/contact");
    	endPoints.put("to get appartments count", "http://localhost:8091/appartments/count");
        return endPoints;
    }
}

