package com.meli.projetointegradormelifrescos.enums;

import com.meli.desafio_quality.exception.NotFoundException;
import com.meli.projetointegradormelifrescos.config.exception.BadRequestException;
import lombok.Getter;

public enum Category {
    FRESCO("FS", "Fresco", 15F, 25F),
    REFRIGERADO("RF", "Refrigerado", 8F, 15F),
    CONGELADO("FF", "Congelado", 0F, 7F);

    @Getter
    private String value;

    @Getter
    private String name;

    @Getter
    private Float minimumTemperature;

    @Getter
    private Float maximumTemperature;


    Category(String value, String name, Float minimumTemperature, Float maximumTemperature) {
        this.value = value;
        this.name = name;
        this.minimumTemperature = minimumTemperature;
        this.maximumTemperature = maximumTemperature;
    }

    public static Category valueOf(int categoryId) {
        if (categoryId > 2 || categoryId < 0) {
            throw new BadRequestException("invalid category");
        }
        return Category.values()[categoryId];
    }

    public static Category getEnumName(String name) {
        try {
            return Category.valueOf(name.toUpperCase());
        } catch (Exception e) {
            throw new BadRequestException("Invalid category");
        }

    }
}


