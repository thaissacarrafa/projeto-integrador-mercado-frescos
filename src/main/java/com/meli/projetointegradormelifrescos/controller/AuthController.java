package com.meli.projetointegradormelifrescos.controller;

import com.meli.projetointegradormelifrescos.dto.LoginDTO;
import com.meli.projetointegradormelifrescos.dto.TokenDTO;
import com.meli.projetointegradormelifrescos.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/auth")
    public ResponseEntity<TokenDTO> login(@RequestBody LoginDTO credentials){
        return ResponseEntity.ok(authService.generateTokenManager(credentials));
    }
}
