package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.dto.AnnoucementDTO;
import com.meli.projetointegradormelifrescos.dto.BatchDTO;
import com.meli.projetointegradormelifrescos.enums.Category;
import com.meli.projetointegradormelifrescos.exception.ListIsEmptyException;
import com.meli.projetointegradormelifrescos.exception.NotFoundException;
import com.meli.projetointegradormelifrescos.model.Announcement;
import org.springframework.beans.factory.annotation.Autowired;

import com.meli.projetointegradormelifrescos.model.Batch;
import com.meli.projetointegradormelifrescos.repository.BatchRepo;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BatchService {
    @Autowired
    BatchRepo batchRepo;

    public void saveBatch(Batch batch) {
        batchRepo.save(batch);
    }

    public BatchDTO findById(Long batchNumber) {

       Optional<Batch> batch = batchRepo.findById(batchNumber);
        return new BatchDTO(batch.get());
    }

//    public Batch listBatchByAlert(Long batchNumber){
//
//       Batch batches = batchRepo.findAll().stream()
//                .filter(batch -> Objects.equals(batch.getBatchNumber(),batchNumber)).
//
//        if(batches.toString().isEmpty()){
//            throw new ListIsEmptyException("No products found");
//        }
//        return batches;
//    }
}
