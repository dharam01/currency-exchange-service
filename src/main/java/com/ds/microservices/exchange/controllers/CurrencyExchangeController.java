package com.ds.microservices.exchange.controllers;

import com.ds.microservices.exchange.entities.ExchangeValue;
import com.ds.microservices.exchange.repositories.ExchangeValueRepository;
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
    private ExchangeValueRepository valueRepository;

    @GetMapping(path = "/currency-exchange/{from}/to/{to}")
    public ExchangeValue getCurrencyExchange(@PathVariable String from, @PathVariable String to) {
        ExchangeValue exchangeValue = valueRepository.findByFromAndTo(from, to);
        exchangeValue.setPort(Integer.valueOf(environment.getProperty("local.server.port")));
        return exchangeValue;
    }
}
