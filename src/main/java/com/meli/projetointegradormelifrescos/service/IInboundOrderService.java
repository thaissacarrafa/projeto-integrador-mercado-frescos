package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.dto.BatchDTO;
import com.meli.projetointegradormelifrescos.dto.InboundOrderDTO;
import com.meli.projetointegradormelifrescos.dto.WarehouseDTO;
import com.meli.projetointegradormelifrescos.model.InboundOrder;

import java.util.List;

public interface IInboundOrderService {

    List<BatchDTO> createInboundOrder(InboundOrderDTO orderDTO);

    List<BatchDTO> updateInboundOrder(Long orderId, InboundOrderDTO orderDTO);


    /**
     * lista todos os pedidos de ordem do armazém
     * @author Amanda Lobo
     */
    List<InboundOrder> getAll();
    /**
     * Método que lista os produtos com todos os lotes onde ele aparece
     * @author Amanda Lobo
     * @param productId -> Long
     * @return List<WarehouseDTO> Retorna uma lista do tipo WarehouseDTO com todos os lotes de um produto em seu armazém e seção.
     * @throws Exception
     */
    List<WarehouseDTO> getAllProductWarehouse(Long productId) throws Exception;


    /**
     * Método que retorna uma lista de lotes ordenados
     * @author Amanda Lobo
     * @param warehouseDTOList -> List<WarehouseDTO>
     * @param sorting -> String
     * @return List<WarehouseDTO> -> Lista do tipo WarehouseDTO ordenada pelo parâmetro requerido*/
    List<WarehouseDTO> getAllOrdinancesFotBatches(List<WarehouseDTO> warehouseDTOList, String sorting);

    /**
     * Método que busca se um setor é dono de um lote
     * @author Amanda Lobo
     * @param productId -> Long
     */
   void findSectionByProductId(Long productId);

}
