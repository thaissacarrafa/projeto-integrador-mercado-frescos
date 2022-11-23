/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */

package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.exception.NotFoundException;
import com.meli.projetointegradormelifrescos.model.Warehouse;
import com.meli.projetointegradormelifrescos.repository.WarehouseRepo;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WarehouseService implements IWarehouseService {

    @Autowired
    private WarehouseRepo repo;

    @Autowired
    private IBatchService batchStockService;

    @Override
    public Warehouse findWarehouseById(Long id) {
        Optional<Warehouse> warehouse = repo.findById(id);
        if (warehouse.isEmpty()) {
            throw new NotFoundException("WarehouseId not found");
        }
        return warehouse.get();
    }

    @Override
    public Warehouse findWarehouseByCode(Long code) {
        Optional<Warehouse> warehouse = repo.findWarehouseByCode(code);
        if (warehouse.isEmpty()) {
            throw new NotFoundException("Warehouse code not found");
        }
        return warehouse.get();
    }
}
