package com.meli.projetointegradormelifrescos.enums.repository;

import com.meli.projetointegradormelifrescos.model.Batch;
import com.meli.projetointegradormelifrescos.model.Section;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SectionRepo extends JpaRepository<Section, Long> {
//    List<Batch> findBatchBysSectionId(Long sectionId, Long batchId);
}
