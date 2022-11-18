package com.meli.projetointegradormelifrescos.enums.repository;

import com.meli.projetointegradormelifrescos.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnoucementRepo extends JpaRepository<Announcement, Long> {

    List<Announcement> findAll();

    List<Announcement> findAllByCategory(String category);
}
