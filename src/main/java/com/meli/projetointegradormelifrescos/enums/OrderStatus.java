package com.meli.projetointegradormelifrescos.enums;

import com.meli.projetointegradormelifrescos.exception.BadRequestException;

public enum OrderStatus {
    OPENED,
    FINISHED;

    public static OrderStatus getEnumName(String name) {
        try {
            return OrderStatus.valueOf(name.toUpperCase());
        } catch (Exception e) {
            throw new BadRequestException("Invalid status");
        }
    }

}
