package com.meli.projetointegradormelifrescos.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.meli.projetointegradormelifrescos.dto.CurrencyDTO;
import com.meli.projetointegradormelifrescos.exception.NotFoundException;

import lombok.Data;

@Data
@Service
public class CurrencyService {
    public BigDecimal getCurrency(String currency) {
        String uri = String.format("https://economia.awesomeapi.com.br/BRL-%s/1?format=json", currency);

        var request = new RestTemplate();
        var dto = request.getForObject(uri, CurrencyDTO[].class);
        if (dto == null) {
            throw new NotFoundException("Currency not found");
        }

        return dto[0].getBid();
    }
}
