package com.example.AnimalShelter.Services;

import com.example.AnimalShelter.Exceptions.NotFoundException;
import com.example.AnimalShelter.Exceptions.UsernameAlreadyInUseException;
import com.example.AnimalShelter.Models.Admin;
import com.example.AnimalShelter.Repositories.AdminRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AdminService {

    AdminRepository adminRepository;
    UserService userService;

    public AdminService(AdminRepository adminRepository, UserService userService) {
        this.adminRepository = adminRepository;
        this.userService = userService;
    }

    public Admin updateAdmin(Admin newAdmin) throws NotFoundException, UsernameAlreadyInUseException {
        Admin admin = adminRepository.findById(newAdmin.getId()).orElseThrow(() -> new NotFoundException("Id not found"));
        if (!isAdminUsernameUnique(newAdmin.getUsername()) && !userService.isUserUsernameUnique(newAdmin.getUsername())) {
            throw new UsernameAlreadyInUseException("Username already in use");
        }

        if (newAdmin.getName() != null)
            admin.setName(newAdmin.getName());
        if (newAdmin.getLocation() != null)
            admin.setLocation(newAdmin.getLocation());
        if (newAdmin.getEmail() != null)
            admin.setEmail(newAdmin.getEmail());
        if (newAdmin.getUsername() != null)
            admin.setUsername(newAdmin.getUsername());
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

    public Admin getAdminByUsername(String username) throws NotFoundException {
        Admin admin = adminRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("Username not found"));
        return admin;
    }

    public List<Admin> getAllAdmins() {
        return adminRepository.findAll();
    }

    public boolean isAdminUsernameUnique(String username) {
        return adminRepository.findByUsername(username).isEmpty();
    }

    public Admin createAdmin(Admin newAdmin) throws UsernameAlreadyInUseException {
        if (!isAdminUsernameUnique(newAdmin.getUsername()) && !userService.isUserUsernameUnique(newAdmin.getUsername())) {
            throw new UsernameAlreadyInUseException("Username already in use");
        }

//TODO: fac verificari (username ul sa fie unic, email sa fie unic)
//TODO: sa verific ca parola are un anumit format (lungime,etc)

        Admin adminToBeSaved = Admin.builder()
                .name(newAdmin.getName())
                .location(newAdmin.getLocation())
                .email(newAdmin.getEmail())
                .username(newAdmin.getUsername())
                .password(newAdmin.getPassword())
                .build();
        adminRepository.save(adminToBeSaved);
        return adminToBeSaved;
    }
}
