package com.meli.projetointegradormelifrescos.enums;

import com.meli.projetointegradormelifrescos.exception.BadRequestException;
import lombok.Getter;

@Getter
public enum Category {
    FRESCO("FS", "Fresco", 15F, 25F),
    RESFRIADO("RF", "Resfriada", 8F, 15F),
    CONGELADO("FF", "Congelado", 0F, 7F);

    private final String value;
    private final String name;
    private final Float minimumTemperature;
    private final Float maximumTemperature;

    Category(
        String value,
        String name,
        Float minimumTemperature,
        Float maximumTemperature
    ) {
        this.value = value;
        this.name = name;
        this.minimumTemperature = minimumTemperature;
        this.maximumTemperature = maximumTemperature;
    }

    public static Category valueOf(int categoryId) {
        if (categoryId > 2 || categoryId < 0) {
            throw new BadRequestException("Invalid category");
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

    public static Category getCategoryByValue(String value) {
        for (Category category : Category.values()) {
            if (category.getValue().equals(value)) return category;
        }
        throw new BadRequestException("Invalid category");
    }
}
