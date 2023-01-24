package com.example.AnimalShelter.Repositories;

import com.example.AnimalShelter.Models.Center;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CenterRepository extends JpaRepository<Center,Long> {

    //TODO: modifica sa intoarca <Optional>
    List<Center> getAllById(int id);
}
