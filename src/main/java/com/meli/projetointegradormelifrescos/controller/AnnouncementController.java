package com.meli.projetointegradormelifrescos.controller;

import com.meli.projetointegradormelifrescos.model.Announcement;
import com.meli.projetointegradormelifrescos.service.IAnnouncementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/fresh-products/")
public class AnnouncementController {

    @Autowired
    private IAnnouncementService service;

    @GetMapping
    public ResponseEntity<List<Announcement>> getAllAnnouncements() {
        return ResponseEntity.ok(service.getAllAnnouncements());
    }

    @GetMapping("/list")
    public ResponseEntity<List<Announcement>> getAllAnnouncementsByCategory(
            @RequestParam String category
    ) {
        return ResponseEntity.ok(service.getAllAnnouncementsByCategory(category));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Announcement>> getAllAnnouncementsById(
            @RequestParam Long id
    ) {
        return ResponseEntity.ok(service.getAnnouncementById(id));
    }

    @GetMapping("/list")
    public ResponseEntity<List<Announcement>> getAllAnnouncementsByBatchNumber(
            @RequestParam Long id, @RequestParam Character sortBy
    ) {
        return ResponseEntity.ok(service.getAllAnnouncementsByBatchNumber(id, sortBy));
    }
}
