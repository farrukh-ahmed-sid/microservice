package com.example.microservices.currencyexchangeservice.dao;

import com.example.microservices.currencyexchangeservice.model.ExchangeValue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CurrencyExchangeDao extends JpaRepository<ExchangeValue, Integer> {

    ExchangeValue findByFromAndTo(String from, String to);
}
