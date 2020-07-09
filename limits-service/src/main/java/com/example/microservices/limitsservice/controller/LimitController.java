package com.example.microservices.limitsservice.controller;

import com.example.microservices.limitsservice.configuration.LimitConfiguration;
import com.example.microservices.limitsservice.model.Limit;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.ribbon.proxy.annotation.Hystrix;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LimitController {

    @Autowired
    private LimitConfiguration limitConfiguration;

    @GetMapping("/limit")
    @HystrixCommand(fallbackMethod = "getDefaultLimit")
    public Limit getLimit(){
        //throw new RuntimeException();
        return new Limit(limitConfiguration.getMaximum(),limitConfiguration.getMinimum());
    }

    public Limit getDefaultLimit(){
        return new Limit(0,-1);
    }
}
