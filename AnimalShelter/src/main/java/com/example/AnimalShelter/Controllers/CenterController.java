package com.example.AnimalShelter.Controllers;

import com.example.AnimalShelter.Exceptions.NotFoundException;
import com.example.AnimalShelter.Models.Center;
import com.example.AnimalShelter.Services.CenterService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/center")

public class CenterController {

    private CenterService centerService;

    public CenterController(CenterService centerService) {
        this.centerService = centerService;
    }


    @PostMapping()
    public Center createCenter(@RequestBody Center newCenter) {
        return centerService.createCenter(newCenter);
    }

    @GetMapping("/centerById")
    public Optional<Center> getCenterbyId(@RequestParam int id) throws NotFoundException {
        return centerService.getCenterById(id);
    }

    @GetMapping("/allCenters")
    public List<Center> getAllCenters() {
        return centerService.getAllCenters();
    }
}
