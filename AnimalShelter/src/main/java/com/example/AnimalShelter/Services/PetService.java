package com.example.AnimalShelter.Services;

import com.example.AnimalShelter.Exceptions.NotFoundException;
import com.example.AnimalShelter.Models.Pet;
import com.example.AnimalShelter.Repositories.PetRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public class PetService {

    PetRepository petRepository;

    public PetService(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    public List<Pet> getPetsByAge(int age){
       return petRepository.getAllByAge(age);
    }

    public Pet getPetById(int id) throws NotFoundException {
        Pet pet = petRepository.getById(id).orElseThrow(()->new NotFoundException("Id not found"));
        return pet;
    }

    public List<Pet> getAllPets(){
        return petRepository.findAll();
    }


    public Pet createPet(){
        Pet a = new Pet();
        a.setName("Martinel");
        a.setAge(7);
        a.setLocation("Bucuresti");
        a.setRace("dog");
        a.setSize("big");
        a.setSex("M");
        a.setBehavior("agresive");
        a.setVaccine(false);
        return a;
    }
}
