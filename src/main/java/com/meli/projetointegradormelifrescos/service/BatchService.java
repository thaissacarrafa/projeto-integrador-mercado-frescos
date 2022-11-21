package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.dto.BatchDTO;
import com.meli.projetointegradormelifrescos.dto.BatchNearDueDateDTO;
import com.meli.projetointegradormelifrescos.enums.repository.SectionRepo;
import com.meli.projetointegradormelifrescos.exception.NotFoundException;
import com.meli.projetointegradormelifrescos.model.Section;
import org.springframework.beans.factory.annotation.Autowired;

import com.meli.projetointegradormelifrescos.model.Batch;
import com.meli.projetointegradormelifrescos.enums.repository.BatchRepo;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BatchService implements IBatchStockService {
    @Autowired
    BatchRepo batchRepo;
    @Autowired
    SectionRepo sectionRepo;


    @Override
    public List<Batch> saveAll(List<Batch> batches) {
        return null;
    }

    @Override
    public List<BatchNearDueDateDTO> getBatchStockBySection(Integer numberOfDays, Long sectionId) {

        Section section = sectionRepo
                .findById(sectionId)
                .orElseThrow(() -> new NotFoundException("Section not found"));

        if (section.getInboundOrders().isEmpty()) throw new NotFoundException(
                "Section without inbound orders"
        );

        List<Batch> batches = new ArrayList<>();

        section
                .getInboundOrders()
                .forEach(inboundOrder -> batches.addAll(inboundOrder.getBatches()));

        List<Batch> filteredBatches = batches
                .stream()
                .filter(batch ->
                        batch.getDueDate().isAfter(LocalDate.now()) &&
                                batch
                                        .getDueDate()
                                        .isBefore(batch.getDueDate().plusDays(numberOfDays))
                )
                .collect(Collectors.toList());

        if (filteredBatches.isEmpty()) throw new NotFoundException(
                "No batches found"
        );

        List<BatchNearDueDateDTO> convertedDTO = filteredBatches.stream().map(batchEntity -> BatchNearDueDateDTO.builder()
                        .batchNumber(batchEntity.getBatchNumber())
                        .dueDate(batchEntity.getDueDate())
                        .productQuantity(batchEntity.getProductQuantity())
                        .productId(batchEntity.getProductId())
                        .ProductTypedId(getTypedIngProduct(batchEntity.getCurrentTemperature()))
                        .build()
                ).collect(Collectors.toList());

        return convertedDTO;
    }

    private String getTypedIngProduct(float currentTemperature){
        String productTypeId = "";
        if (currentTemperature > 15f || currentTemperature < 25f) {
            return productTypeId = "FRESCO";
        } else if (currentTemperature > 8f || currentTemperature < 15f) {
            return productTypeId = "Refrigerado";
        } else if (currentTemperature > 0f || currentTemperature < 7f) {
            return productTypeId = "Congelado";
        }

        return productTypeId;
    }
    public void saveBatch(Batch batch) {
        batchRepo.save(batch);
    }

}
