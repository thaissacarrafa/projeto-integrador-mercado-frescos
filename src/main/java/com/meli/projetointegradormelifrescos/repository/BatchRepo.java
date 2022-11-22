
package com.meli.projetointegradormelifrescos.repository;

import com.meli.projetointegradormelifrescos.model.Batch;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;



/*
 * Copyright (c) 2022. Lorem ipsum dolor sit amet, consectetur adipiscing elit.
 * Morbi non lorem porttitor neque feugiat blandit. Ut vitae ipsum eget quam lacinia accumsan.
 * Etiam sed turpis ac ipsum condimentum fringilla. Maecenas magna.
 * Proin dapibus sapien vel ante. Aliquam erat volutpat. Pellentesque sagittis ligula eget metus.
 * Vestibulum commodo. Ut rhoncus gravida arcu.
 */
public interface BatchRepo extends JpaRepository<Batch, Long> {
    List<Batch> findAllByProductId(Long productId);
    List<Batch> findBatchByProductId(Long productId);
    List<Batch> findSectionByProductId(Long productId);

    List<Batch> findAllByDueDateBetween(
        LocalDate today,
        LocalDate dueDate
    );







}
