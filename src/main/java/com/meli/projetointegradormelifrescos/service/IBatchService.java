package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.dto.WarehouseStockDTO;


public interface IBatchService {

        public WarehouseStockDTO countStocksByProductId(Long productId);

}
