package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.dto.LoginDTO;
import com.meli.projetointegradormelifrescos.dto.TokenDTO;
import com.meli.projetointegradormelifrescos.exception.BadCredentialsException;
import com.meli.projetointegradormelifrescos.untils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    private JWTUtils jwtUtils;

    /**
     * verifica os objetos passados no body da requisição para gerar um token
     * caso login e senha não seja o manager simulado lançará uma exception
     * @throws BadCredentialsException
     * @param login
     * @autor Igor S. Fernandes
     * @return String
     */
    public TokenDTO generateTokenManager(LoginDTO login){
        String MockManager = "eusoumanager@gmail.com";
        String PasswordManagerMock = "123456";

        if(!PasswordManagerMock.equals(login.getPassword())){
            throw new BadCredentialsException("Senha inválida!");
        }

        if(!MockManager.equals(login.getUsername())){
            throw new BadCredentialsException("Email inválido!");
        }

        String token = jwtUtils.generateToken(login.getUsername());
        return new TokenDTO(token);}

}
