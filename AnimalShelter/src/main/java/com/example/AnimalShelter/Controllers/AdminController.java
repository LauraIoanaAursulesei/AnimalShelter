package com.example.AnimalShelter.Controllers;

import com.example.AnimalShelter.Exceptions.NotFoundException;
import com.example.AnimalShelter.Models.Admin;
import com.example.AnimalShelter.Models.Pet;
import com.example.AnimalShelter.Services.AdminService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/admin")

public class AdminController {

    private AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping()
    public Admin createAdmin() {
        return adminService.createAdmin();
    }

    @GetMapping("/adminById")
    public Admin getAdminById(@RequestParam int id) throws NotFoundException {
        return adminService.getAdminById(id);
    }

    @GetMapping("/allAdmins")
    public List<Admin> getAllAdmins() {
        return adminService.getAllAdmins();
    }
}
