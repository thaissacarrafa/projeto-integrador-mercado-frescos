package com.meli.projetointegradormelifrescos.enums;

public enum Category {
    FRESCO("FS"),
    REFRIGERADO("RF"),
    CONGELADO("FF");

    private String value;

    Category(String value) {
        this.value = value;
    }

    public String getCategory() {
        return value;
    }

}