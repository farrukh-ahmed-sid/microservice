package com.example.currencyconversionservice.model;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CurrencyConversionBean {

    private Integer id;
    private String from;
    private String to;
    private String conversionMultiple;
    private Double quantity;
    private Double totalCalculatedAmount;
    private Integer port;

    public CurrencyConversionBean(Integer id, String from, String to, String conversionMultiple, Double quantity, Double totalCalculatedAmount) {
        this.id = id;
        this.from = from;
        this.to = to;
        this.conversionMultiple = conversionMultiple;
        this.quantity = quantity;
        this.totalCalculatedAmount = totalCalculatedAmount;
    }
}
