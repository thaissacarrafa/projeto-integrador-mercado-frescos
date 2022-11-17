package com.meli.projetointegradormelifrescos.repository;

import com.meli.projetointegradormelifrescos.enums.Category;
import com.meli.projetointegradormelifrescos.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AnnoucementRepo extends JpaRepository<Announcement, Long> {

    List<Announcement> findAll();

    List<Announcement> findAllByCategory(Category category);
}
