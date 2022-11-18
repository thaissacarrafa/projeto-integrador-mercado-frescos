package com.meli.projetointegradormelifrescos.service;

import com.meli.projetointegradormelifrescos.model.Announcement;

import java.util.List;

public interface IAnnouncementService {

    List<Announcement> getAllAnnouncements();
    List<Announcement> getAnnouncementById(Long id);
    List<Announcement> getAllAnnouncementsByCategory(String category);
    List<Announcement> getAllAnnouncementsByBatchNumber(Long id, Character sortBy);
}
