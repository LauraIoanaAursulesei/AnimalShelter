package com.example.AnimalShelter.Services;

import com.example.AnimalShelter.Exceptions.NotFoundException;
import com.example.AnimalShelter.Exceptions.AlreadyInUseException;
import com.example.AnimalShelter.Models.Admin;
import com.example.AnimalShelter.Repositories.AdminRepository;
import com.example.AnimalShelter.Repositories.CenterRepository;
import com.example.AnimalShelter.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class AdminService {

    AdminRepository adminRepository;
    UserRepository userRepository;
    CenterRepository centerRepository;

    public AdminService(AdminRepository adminRepository, UserRepository userRepository, CenterRepository centerRepository) {
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
        this.centerRepository = centerRepository;
    }

    public Admin updateAdmin(Admin newAdmin) throws NotFoundException, AlreadyInUseException {
        Admin admin = adminRepository.findById(newAdmin.getId()).orElseThrow(() -> new NotFoundException("Id not found"));
        if (!isAdminUsernameUnique(newAdmin.getUsername()) && !isUserUsernameUnique(newAdmin.getUsername())) {
            throw new AlreadyInUseException("Username already in use");
        }
        if (!isAdminEmailUnique(newAdmin.getEmail()) && !isUserEmailUnique(newAdmin.getEmail()) && !isCenterEmailUnique(newAdmin.getEmail())) {
            throw new AlreadyInUseException("Email already in use");
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

    public boolean isAdminEmailUnique(String email) {
        return adminRepository.findByEmail(email).isEmpty();
    }

    public boolean isUserEmailUnique(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }

    public boolean isCenterEmailUnique(String email) {
        return centerRepository.findByEmail(email).isEmpty();
    }

    public boolean isAdminUsernameUnique(String username) {
        return adminRepository.findByUsername(username).isEmpty();
    }

    public boolean isUserUsernameUnique(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }

    public Admin createAdmin(Admin newAdmin) throws AlreadyInUseException {
        if (!isAdminUsernameUnique(newAdmin.getUsername()) && !isUserUsernameUnique(newAdmin.getUsername())) {
            throw new AlreadyInUseException("Username already in use");
        }
        if (!isAdminEmailUnique(newAdmin.getEmail()) && !isUserEmailUnique(newAdmin.getEmail()) && !isCenterEmailUnique(newAdmin.getEmail())) {
            throw new AlreadyInUseException("Email already in use");
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
