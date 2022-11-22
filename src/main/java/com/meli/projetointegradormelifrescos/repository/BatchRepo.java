package com.meli.projetointegradormelifrescos.repository;

import com.meli.projetointegradormelifrescos.model.Announcement;
import com.meli.projetointegradormelifrescos.model.Batch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface BatchRepo extends JpaRepository<Batch, Long> {
//    List<Batch> findAllByProductId(Announcement id);
}
