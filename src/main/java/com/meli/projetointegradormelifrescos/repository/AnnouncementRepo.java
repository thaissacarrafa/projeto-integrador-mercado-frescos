package com.meli.projetointegradormelifrescos.repository;

import com.meli.projetointegradormelifrescos.model.Announcement;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AnnouncementRepo extends JpaRepository<Announcement, Long> {
}
