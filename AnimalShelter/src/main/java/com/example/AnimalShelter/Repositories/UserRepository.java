package com.example.AnimalShelter.Repositories;

import com.example.AnimalShelter.Models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    Optional<User> findById(Long id);
    List<User> getAllByName(String name);
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
