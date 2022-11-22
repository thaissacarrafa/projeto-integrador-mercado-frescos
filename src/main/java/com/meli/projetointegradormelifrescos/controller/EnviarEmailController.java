package com.meli.projetointegradormelifrescos.controller;

import com.meli.projetointegradormelifrescos.service.JavaMailApp;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/enviar")
public class EnviarEmailController {

    @PostMapping
    public void enviar(){
        JavaMailApp.welcome();
    }
}
