package com.meli.projetointegradormelifrescos.enums;

public enum ProductCategory {
    FRESCO("FS"),
    REFRIGERADO("RF"),
    CONGELADO("FF");

    private String value;

    ProductCategory(String value) {
        this.value = value;
    }

    public String getProductCategory() {
        return value;
    }

}