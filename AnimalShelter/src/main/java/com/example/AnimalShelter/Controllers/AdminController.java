package com.example.AnimalShelter.Controllers;

import com.example.AnimalShelter.Exceptions.NotFoundException;
import com.example.AnimalShelter.Exceptions.UsernameAlreadyInUseException;
import com.example.AnimalShelter.Models.Admin;
import com.example.AnimalShelter.Services.AdminService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")

public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @PutMapping()
    public Admin updateAdmin(@RequestBody Admin newAdmin) throws NotFoundException, UsernameAlreadyInUseException {
        return adminService.updateAdmin(newAdmin);
    }

    @DeleteMapping()
    public void deleteAdmin(Long id) throws NotFoundException {
        adminService.deleteAdmin(id);
    }

    @PostMapping()
    public Admin createAdmin(@RequestBody Admin newAdmin) throws UsernameAlreadyInUseException {
        return adminService.createAdmin(newAdmin);
    }

    @GetMapping("/adminById")
    public Admin getAdminById(@RequestParam Long id) throws NotFoundException {
        return adminService.getAdminById(id);
    }

    @GetMapping("/adminByUsername")
    public Admin getAdminByUsername(@RequestParam String username) throws NotFoundException {
        return adminService.getAdminByUsername(username);
    }

    @GetMapping("/allAdmins")
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }
}
