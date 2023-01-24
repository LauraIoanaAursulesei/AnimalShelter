package com.example.AnimalShelter.Services;

import com.example.AnimalShelter.Exceptions.NotFoundException;
import com.example.AnimalShelter.Models.User;
import com.example.AnimalShelter.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UserService {

    UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> getUserById(int id) throws NotFoundException {
        Optional<User> user;
        if (userRepository.getById(id).isEmpty())
            throw new NotFoundException("Id not found");
        else
            user = userRepository.getById(id);
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUsersByName(String name) {
        return userRepository.getAllByName(name);
    }

    public User createUser(User newUser) {

        User userToBeSaved = User.builder()
                .name(newUser.getName())
                .age(newUser.getAge())
                .email(newUser.getEmail())
                .password(newUser.getPassword())
                .username(newUser.getUsername())
                .address(newUser.getAddress())
                .build();
        userRepository.save(userToBeSaved);

//        User a = new User();
//        a.setName("Ion");
//        a.setAge(24);
//        a.setUsername("ionutz");
//        a.setEmail("ion24@gmail.com");
//        a.setPassword("parola");
//        a.setAddress("Bucuresti");
        return userToBeSaved;
    }
}
