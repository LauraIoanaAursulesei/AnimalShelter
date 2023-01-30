package com.example.AnimalShelter.Services;

import com.example.AnimalShelter.Exceptions.NotFoundException;
import com.example.AnimalShelter.Models.Admin;
import com.example.AnimalShelter.Repositories.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AdminService {

    AdminRepository adminRepository;

    public AdminService(AdminRepository adminRepository) {
        this.adminRepository = adminRepository;
    }

    public Admin updateAdmin(Admin newAdmin) throws NotFoundException {
        Admin admin = adminRepository.findById(newAdmin.getId()).orElseThrow(() -> new NotFoundException("Id not found"));

        if (newAdmin.getName() != null)
            admin.setName(newAdmin.getName());
        if (newAdmin.getLocation() != null)
            admin.setLocation(newAdmin.getLocation());
        if (newAdmin.getEmail() != null)
            admin.setEmail(newAdmin.getEmail());
        if (newAdmin.getPassword() != null)
            admin.setPassword(newAdmin.getPassword());

        return adminRepository.save(admin);
    }

    public void deleteAdmin(Long id) throws NotFoundException {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new NotFoundException("Id not found"));
        adminRepository.delete(admin);
    }

    public Admin getAdminById(Long id) throws NotFoundException {
        Admin admin = adminRepository.findById(id).orElseThrow(() -> new NotFoundException("Id not found"));
        return admin;
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public Admin createAdmin(Admin newAdmin) {

//TODO: fac verificari (username ul sa fie unic, email sa fie unic)
//TODO: sa verific ca parola are un anumit format (lungime,etc)

        Admin adminToBeSaved = Admin.builder()
                .name(newAdmin.getName())
                .location(newAdmin.getLocation())
                .email(newAdmin.getEmail())
                .password(newAdmin.getPassword())
                .build();
        adminRepository.save(adminToBeSaved);
        return adminToBeSaved;
    }
}
