package com.example.AnimalShelter.Controllers;

import com.example.AnimalShelter.Exceptions.AlreadyInUseException;
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

    @PutMapping()
    public Center updateCenter(@RequestBody Center newCenter) throws NotFoundException, AlreadyInUseException {
        return centerService.updateCenter(newCenter);
    }

    @DeleteMapping()
    public void deleteCenter(Long id) throws NotFoundException {
        centerService.deleteCenter(id);
    }

    @PostMapping()
    public Center createCenter(@RequestBody Center newCenter) throws AlreadyInUseException {
        return centerService.createCenter(newCenter);
    }

    @GetMapping("/centerById")
    public Optional<Center> getCenterById(@RequestParam Long id) throws NotFoundException {
        return centerService.getCenterById(id);
    }

    @GetMapping("/allCenters")
    public List<Center> getAllCenters() {
        return centerService.getAllCenters();
    }
}
