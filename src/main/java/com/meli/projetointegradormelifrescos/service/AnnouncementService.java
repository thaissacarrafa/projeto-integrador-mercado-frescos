package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.enums.Category;
import com.meli.projetointegradormelifrescos.exception.NotFoundException;
import com.meli.projetointegradormelifrescos.model.Announcement;
import com.meli.projetointegradormelifrescos.repository.AnnouncementRepo;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AnnouncementService implements IAnnouncementService {

    @Autowired
    private AnnouncementRepo announcementRepo;

    /**
     * Get all announcements
     * @return List of announcements
     */
    @Override
    public List<Announcement> getAllAnnouncements() {
        List<Announcement> announcements = announcementRepo.findAll();

        if (announcements.isEmpty()) {
            throw new NotFoundException("No announcements found");
        }

        return announcements;
    }

    /**
     * Get all announcements by id
     * @param id Announcement id
     * @return List of a single announcement
     */
    @Override
    public List<Announcement> getAnnouncementById(Long id) {
        Announcement announcement = announcementRepo
            .findById(id)
            .orElseThrow(() -> new NotFoundException("Announcement not found"));

        return List.of(announcement);
    }

    /**
     * Get all announcements by category
     * @param category Category to filter
     * @return List of announcements
     */
    @Override
    public List<Announcement> getAllAnnouncementsByCategory(String category) {
        List<Announcement> announcements = announcementRepo.findByCategory(
            Category.valueOf(category.toUpperCase())
        );

        if (announcements.isEmpty()) {
            throw new NotFoundException(
                "No announcements found for category " + category
            );
        }

        return announcements;
    }

    // TODO: Feature 3
    @Override
    public List<Announcement> getAllAnnouncementsByBatchNumber(
        Long id,
        Character sortBy
    ) {
        return null;
    }
}
