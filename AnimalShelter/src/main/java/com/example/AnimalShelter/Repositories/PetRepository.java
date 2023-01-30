package com.example.AnimalShelter.Repositories;

import com.example.AnimalShelter.Models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet,Long> {

   List<Pet> getAllByAge(int age);
   Optional<Pet> findById(Long id);

}
