package com.example.AnimalShelter.Controllers;

import com.example.AnimalShelter.Models.Center;
import com.example.AnimalShelter.Models.Pet;
import com.example.AnimalShelter.Models.User;
import com.example.AnimalShelter.Services.CenterService;
import com.example.AnimalShelter.Services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/center")

public class CenterController {

    private CenterService centerService;

    public CenterController(CenterService centerService) {
        this.centerService = centerService;
    }

    @GetMapping()
    public Center createCenter() {
        return centerService.createCenter();
    }

    @GetMapping("/centerById")
    public List<Center> getCentersbyId(@RequestParam int id) {
        return centerService.getCentersbyId(id);
    }

    @GetMapping("/allCenters")
    public List<Center> getAllCenters() {
        return centerService.getAllCenters();
    }
}
