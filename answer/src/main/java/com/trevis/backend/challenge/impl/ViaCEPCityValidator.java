package com.trevis.backend.challenge.impl;

import org.springframework.web.client.RestTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import com.trevis.backend.challenge.dto.ViaCEPJson;
import com.trevis.backend.challenge.services.CityValidator;

public class ViaCEPCityValidator implements CityValidator {
    
    @Autowired
    private RestTemplate rest;

    @Override
    public boolean validate(String cep, String city) {
        String url = "http://viacep.com.br/ws/" + cep + "/json";
        ViaCEPJson obj = rest.getForObject(url, ViaCEPJson.class);
        return obj.localidade() == city;
    }
}