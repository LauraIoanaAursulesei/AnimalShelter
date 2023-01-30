package com.example.AnimalShelter.Controllers;

import com.example.AnimalShelter.Exceptions.NotFoundException;
import com.example.AnimalShelter.Models.Pet;
import com.example.AnimalShelter.Services.PetService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pet")
public class PetController {

    private PetService petService;

    public PetController(PetService petService) {
        this.petService = petService;
    }

    @PutMapping()
    public Pet updatePet(@RequestBody Pet newPet) throws NotFoundException {
        return petService.updatePet(newPet);
    }

    @DeleteMapping()
    public void deletePet(Long id) throws NotFoundException {
        petService.deletePet(id);
    }

    @PostMapping()
    public Pet createPet(@RequestBody Pet newPet) {
        return petService.createPet(newPet);
    }

    @GetMapping("/petsByAge")
    public List<Pet> getPetsByAge(@RequestParam int age) {
        return petService.getPetsByAge(age);
    }

    @GetMapping("/petById")
    public ResponseEntity<Pet> getPetById(@RequestParam Long id) throws NotFoundException {
        return new ResponseEntity<>(petService.getPetById(id), HttpStatus.OK);
    }

    @GetMapping("/allPets")
    public List<Pet> getAllPets() {
        return petService.getAllPets();
    }
}
