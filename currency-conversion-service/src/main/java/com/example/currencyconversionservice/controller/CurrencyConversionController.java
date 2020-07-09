package com.example.currencyconversionservice.controller;

import com.example.currencyconversionservice.model.CurrencyConversionBean;
import com.example.currencyconversionservice.proxy.CurrencyExchangeServiceProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@RestController
public class CurrencyConversionController {

    @Autowired
    Environment environment;

    @Autowired
    CurrencyExchangeServiceProxy exchangeServiceProxy;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @GetMapping("currency-converter/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertCurrency(@PathVariable String from,
                                                  @PathVariable String to,
                                                  @PathVariable Double quantity){

  /*      Map<String, String> uriVariables = new HashMap<>();
        uriVariables.put("from", from);
        uriVariables.put("to", to);

        ResponseEntity<CurrencyConversionBean> entity = new RestTemplate().getForEntity(
                "http://localhost:8000/currency-exchange/{from}/to/{to}",
                CurrencyConversionBean.class,
                uriVariables);
*/
        CurrencyConversionBean bean = exchangeServiceProxy.getExchangeValue(from, to);//entity.getBody();

        logger.info("logging bean: {}", bean);

        CurrencyConversionBean currencyConversionBean = new CurrencyConversionBean(
                1, from, to, bean.getConversionMultiple(), quantity,
                quantity * Double.parseDouble(bean.getConversionMultiple()));
        String port = environment.getProperty("local.server.port");
        currencyConversionBean.setPort(bean.getPort());

        return currencyConversionBean;
    }
}
