package com.meli.projetointegradormelifrescos.repository;

//import com.meli.projetointegradormelifrescos.enums.Category;
import com.meli.projetointegradormelifrescos.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface AnnouncementRepo extends JpaRepository<Announcement, Long> {

    List<Announcement> findAll();

    List<Announcement> findAllByCategory(String category);

    @Query(value = "select a from Announcement a where a.avarageEvaluation > :minimumEvaluation")
    List<Announcement> finAllByMinimumEvaluation(Double minimumEvaluation);
}
