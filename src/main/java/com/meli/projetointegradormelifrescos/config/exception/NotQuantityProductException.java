package com.meli.projetointegradormelifrescos.config.exception;

public class NotQuantityProductException extends RuntimeException {
    public NotQuantityProductException(String message){
        super(message);
    }
}
