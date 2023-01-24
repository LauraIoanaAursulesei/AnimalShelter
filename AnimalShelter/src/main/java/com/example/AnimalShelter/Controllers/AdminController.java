package com.example.AnimalShelter.Controllers;

import com.example.AnimalShelter.Exceptions.NotFoundException;
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

    @PostMapping()
    public Admin createAdmin(@RequestBody Admin newAdmin) {
        return adminService.createAdmin(newAdmin);
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
