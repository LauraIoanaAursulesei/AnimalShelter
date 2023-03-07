package com.example.AnimalShelter.Repositories;

import com.example.AnimalShelter.Dtos.GetPetDto;
import com.example.AnimalShelter.Models.Pet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<Pet,Long> {

   List<Pet> getAllByAge(int age);
   Optional<Pet> findById(Long id);

   @Query("SELECT new com.example.AnimalShelter.Dtos.GetPetDto(p.name,p.age,p.location,p.race,p.size,p.sex,p.behavior,p.vaccine,p.center.name) FROM Pet p WHERE p.id =:id ")
   Optional<GetPetDto> findGetPetDtoById(Long id);
}
