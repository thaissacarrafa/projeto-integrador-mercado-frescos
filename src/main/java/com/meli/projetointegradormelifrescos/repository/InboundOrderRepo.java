package com.meli.projetointegradormelifrescos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meli.projetointegradormelifrescos.model.InboundOrder;

public interface InboundOrderRepo extends JpaRepository<InboundOrder, Long> {
    InboundOrder getById(long id);
}
