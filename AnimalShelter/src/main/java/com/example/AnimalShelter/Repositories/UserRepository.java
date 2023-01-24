package com.example.AnimalShelter.Repositories;

import com.example.AnimalShelter.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> getById(int id);
    List<User> getAllByName(String name);
}
