package com.example.AnimalShelter.Services;

import com.example.AnimalShelter.Exceptions.NotFoundException;
import com.example.AnimalShelter.Models.Admin;
import com.example.AnimalShelter.Repositories.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class AdminService {

    AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin getAdminById(int id) throws NotFoundException {
        Admin admin = adminRepository.getById(id).orElseThrow(() -> new NotFoundException("Id not found"));
        return admin;
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin createAdmin() {
        Admin a = new Admin();
        a.setName("Mihai");
        a.setLocation("Bucuresti");
        a.setEmail("mailmihai@gmail.com");
        a.setPassword("sjhgfdsjhy");
        return a;
    }
}
