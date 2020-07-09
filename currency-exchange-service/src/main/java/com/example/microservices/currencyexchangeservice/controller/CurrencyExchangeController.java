package com.example.microservices.currencyexchangeservice.controller;

import com.example.microservices.currencyexchangeservice.dao.CurrencyExchangeDao;
import com.example.microservices.currencyexchangeservice.model.ExchangeValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeDao currencyExchangeDao;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("/currency-exchange/{from}/to/{to}")
    public ExchangeValue getExchangeValue(@PathVariable String from, @PathVariable String to){
        ExchangeValue exchangeValue = currencyExchangeDao.findByFromAndTo(from, to);
        String port = environment.getProperty("local.server.port");
        exchangeValue.setPort(Integer.parseInt(port));
        logger.info("logging currency-exchange value: {}" ,exchangeValue);
        throw new RuntimeException();
        //return exchangeValue;
    }
}
