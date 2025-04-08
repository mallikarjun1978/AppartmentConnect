package com.example.demo.controller;

import com.example.demo.entity.AppartmentImages;
import com.example.demo.repository.AppartmentImagesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@RestController
@RequestMapping("api/v1")
public class AppartmentImagesController {

    @Autowired
    private AppartmentImagesRepository repository;

    // Get all apartment images
    @GetMapping
    public ResponseEntity<List<AppartmentImages>> getAllImages() {
        List<AppartmentImages> images = repository.findAll();
        return ResponseEntity.ok(images);
    }

    // Get an image by ID
    @GetMapping("/getappartmentimagesbyid/{id}")
    public ResponseEntity<AppartmentImages> getImageById(@PathVariable int id) {
        Optional<AppartmentImages> image = repository.findById(id);
        return image.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new apartment image
    @PostMapping("/addappartmentimages")
    public ResponseEntity<AppartmentImages> createImage(@RequestBody AppartmentImages appartmentImages) {
        AppartmentImages savedImage = repository.save(appartmentImages);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedImage);
    }

    // Delete an image by ID
    @DeleteMapping("/deleteappartmentimagesbyid/{id}")
    public ResponseEntity<Void> deleteImage(@PathVariable int id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update an apartment image by ID
    @PutMapping("/updateappartmentimagesbyid/{id}")
    public ResponseEntity<AppartmentImages> updateImage(
            @PathVariable int id, @RequestBody AppartmentImages updatedImage) {
        Optional<AppartmentImages> existingImageOptional = repository.findById(id);
        if (existingImageOptional.isPresent()) {
            AppartmentImages existingImage = existingImageOptional.get();
            existingImage.setAppartmentId(updatedImage.getAppartmentId());
            existingImage.setImageUrl(updatedImage.getImageUrl());
            AppartmentImages savedImage = repository.save(existingImage);
            return ResponseEntity.ok(savedImage);
        } else {
            return ResponseEntity.notFound().build();
        }
        
        
        
    }
}
