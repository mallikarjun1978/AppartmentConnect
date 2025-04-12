package com.example.demo.serviceimpl;

import com.example.demo.entity.Apartments;
import com.example.demo.repository.ApartmentsRepository;
import com.example.demo.service.ApartmentsService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class ApartmentsServiceImpl implements ApartmentsService {

    @Autowired
    private ApartmentsRepository apartmentsRepository;

    private static final String UPLOAD_DIR = "uploads/";

    @Override
    public void addApartments(Apartments apartment, MultipartFile imageFile) throws IOException {
        if (imageFile != null && !imageFile.isEmpty()) {
            createUploadDirIfNotExists();

            String uniqueFilename = generateUniqueFilename(imageFile.getOriginalFilename());
            Path filePath = Paths.get(UPLOAD_DIR, uniqueFilename);
            Files.copy(imageFile.getInputStream(), filePath);

            apartment.setImageUrl("/uploads/" + uniqueFilename);
        }

        apartmentsRepository.save(apartment);
    }

    @Override
    public List<Apartments> getAllApartments() {
        return apartmentsRepository.findAll();
    }

    @Override
    public boolean isApartmentsExist(int id) {
        return apartmentsRepository.existsById(id);
    }

    @Override
    public Optional<Apartments> getApartmentsById(int id) {
        return apartmentsRepository.findById(id);
    }

    @Override
    public boolean deleteApartments(int id) {
        if (apartmentsRepository.existsById(id)) {
            apartmentsRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @Override
    public boolean updateApartments(Apartments updatedApartment) {
        if (apartmentsRepository.existsById(updatedApartment.getId())) {
            apartmentsRepository.save(updatedApartment);
            return true;
        }
        return false;
    }

    @Override
    public long getTotalApartments() {
        return apartmentsRepository.count();
    }

    @Override
    public long getAvailableApartments() {
        return apartmentsRepository.findAll().stream()
                .filter(Apartments::getIsAvailable)
                .count();
    }

    @Override
    public boolean updateApartmentsWithImage(Apartments updatedApartment, MultipartFile imageFile) throws IOException {
        Optional<Apartments> existing = apartmentsRepository.findById(updatedApartment.getId());
        if (existing.isPresent()) {
            Apartments apartment = existing.get();
            apartment.setName(updatedApartment.getName());
            apartment.setLocation(updatedApartment.getLocation());
            apartment.setPrice(updatedApartment.getPrice());
            apartment.setIsAvailable(updatedApartment.getIsAvailable());
            apartment.setDescription(updatedApartment.getDescription());

            if (imageFile != null && !imageFile.isEmpty()) {
                createUploadDirIfNotExists();

                String uniqueFilename = generateUniqueFilename(imageFile.getOriginalFilename());
                Path filePath = Paths.get(UPLOAD_DIR, uniqueFilename);
                Files.copy(imageFile.getInputStream(), filePath);

                apartment.setImageUrl("/uploads/" + uniqueFilename);
            }

            apartmentsRepository.save(apartment);
            return true;
        }
        return false;
    }

    // === Utility methods ===

    private void createUploadDirIfNotExists() {
        File uploadDir = new File(UPLOAD_DIR);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
    }

    private String generateUniqueFilename(String originalFilename) {
        return System.currentTimeMillis() + "" + originalFilename.replaceAll("\\s+", "");
    }
}