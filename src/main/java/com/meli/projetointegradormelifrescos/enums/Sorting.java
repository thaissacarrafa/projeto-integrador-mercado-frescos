package com.meli.projetointegradormelifrescos.enums;

import com.meli.projetointegradormelifrescos.exception.NotFoundException;

public enum Sorting {
    L, Q, V;

    public static Sorting sortingEnum(String s){
        Sorting sorting;

        try{
            sorting = Sorting.valueOf(s);
        } catch (Exception e){
            throw new NotFoundException("not found");
        }
        return sorting;
    }
}
