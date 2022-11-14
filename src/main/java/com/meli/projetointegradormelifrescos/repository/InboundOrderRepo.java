package com.meli.projetointegradormelifrescos.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.meli.projetointegradormelifrescos.model.InboundOrder;

@Repository
public interface InboundOrderRepo extends JpaRepository<InboundOrder, Long> {
    InboundOrder save(InboundOrder inboundOrder);
    InboundOrder getById(long id);
}

