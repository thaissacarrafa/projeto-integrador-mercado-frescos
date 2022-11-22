package com.meli.projetointegradormelifrescos.repository;

import com.meli.projetointegradormelifrescos.model.Feedback;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FeedbackRepo extends JpaRepository<Feedback, Long> {
}
