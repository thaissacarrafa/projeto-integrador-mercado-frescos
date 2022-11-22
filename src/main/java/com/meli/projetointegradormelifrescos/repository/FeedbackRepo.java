package com.meli.projetointegradormelifrescos.repository;

import com.meli.projetointegradormelifrescos.model.Announcement;
import com.meli.projetointegradormelifrescos.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface FeedbackRepo extends JpaRepository<Feedback, Long> {
    @Query(value = "select a from Announcement a where a.avarageEvaluation > :filtredEvaluation")
    List<Announcement> finAllByEvaluation(Double filtredEvaluation);
}
