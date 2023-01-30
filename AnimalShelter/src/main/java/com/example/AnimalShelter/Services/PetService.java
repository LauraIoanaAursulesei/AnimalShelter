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

    public Pet updatePet(Pet newPet) throws NotFoundException {
        Pet pet = petRepository.findById(newPet.getId()).orElseThrow(() -> new NotFoundException("Id not found"));

        if (newPet.getName() != null)
            pet.setName(newPet.getName());
        if (newPet.getAge() != null)
            pet.setAge(newPet.getAge());
        if (newPet.getLocation() != null)
            pet.setLocation(newPet.getLocation());
        if (newPet.getRace() != null)
            pet.setRace(newPet.getRace());
        if (newPet.getSize() != null)
            pet.setSize(newPet.getSize());
        if (newPet.getSex() != null)
            pet.setSex(newPet.getSex());
        if (newPet.getBehavior() != null)
            pet.setBehavior(newPet.getBehavior());
        if (newPet.getVaccine() != null)
            pet.setVaccine(newPet.getVaccine());

       return petRepository.save(pet);

    }

    public void deletePet(Long id) throws NotFoundException {
        if (petRepository.findById(id).isEmpty())
            throw new NotFoundException("Id not found");
        petRepository.deleteById(id);
    }

    public List<Pet> getPetsByAge(int age) {
        return petRepository.getAllByAge(age);
    }

    public Pet getPetById(Long id) throws NotFoundException {
        Pet pet = petRepository.findById(id).orElseThrow(() -> new NotFoundException("Id not found"));
        return pet;
    }

    public List<Pet> getAllPets() {
        return petRepository.findAll();
    }


    public Pet createPet(Pet newPet) {

        Pet petToBeSaved = Pet.builder()
                .name(newPet.getName())
                .age(newPet.getAge())
                .location(newPet.getLocation())
                .race(newPet.getRace())
                .size(newPet.getSize())
                .sex(newPet.getSex())
                .behavior(newPet.getBehavior())
                .vaccine(newPet.getVaccine())
                .build();
        petRepository.save(petToBeSaved);
        return petToBeSaved;
    }
}
