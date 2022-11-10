package com.meli.projetointegradormelifrescos.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.meli.projetointegradormelifrescos.model.InboundOrder;

import java.util.Optional;

public interface InboundOrderRepo extends JpaRepository<InboundOrder,Integer> {

}