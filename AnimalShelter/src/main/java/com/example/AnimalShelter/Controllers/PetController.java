package com.example.AnimalShelter.Controllers;

import com.example.AnimalShelter.Exceptions.NotFoundException;
import com.example.AnimalShelter.Models.Pet;
import com.example.AnimalShelter.Services.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/pet")

public class PetController {

    private PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @GetMapping()
    public Pet createPet() {
        return petService.createPet();
    }

    @GetMapping("/petsByAge")
    public List<Pet> getPetsByAge(@RequestParam int age) {
        return petService.getPetsByAge(age);
    }

    @GetMapping("/petById")
    public ResponseEntity<Pet> getPetById(@RequestParam int id) throws NotFoundException {
        return new ResponseEntity<>(petService.getPetById(id), HttpStatus.OK) ;
    }

    @GetMapping("/allPets")
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }
}
