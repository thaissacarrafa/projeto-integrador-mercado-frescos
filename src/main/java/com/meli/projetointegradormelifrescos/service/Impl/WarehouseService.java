package com.meli.projetointegradormelifrescos.service.Impl;

import com.meli.projetointegradormelifrescos.enums.repository.WarehouseRepo;
import com.meli.projetointegradormelifrescos.service.IWarehouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class WarehouseService implements IWarehouseService {

    @Autowired
    private WarehouseRepo repository;

}
