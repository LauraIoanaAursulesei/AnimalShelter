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

    public User updateUser(User newUser) throws NotFoundException {
        User user = userRepository.findById(newUser.getId()).orElseThrow(() -> new NotFoundException("Id not found"));

        if (newUser.getName() != null)
            user.setName(newUser.getName());
        if (newUser.getAge() != null)
            user.setAge(newUser.getAge());
        if (newUser.getEmail() != null)
            user.setEmail(newUser.getEmail());
        if (newUser.getPassword() != null)
            user.setPassword(newUser.getPassword());
        if (newUser.getUsername() != null)
            user.setUsername(newUser.getUsername());
        if (newUser.getAddress() != null)
            user.setAddress(newUser.getAddress());

        return userRepository.save(user);
    }

    public void deleteUser(Long id) throws NotFoundException {
        User user = userRepository.findById(id).orElseThrow(() -> new NotFoundException("Id not found"));
        userRepository.delete(user);
    }

    public Optional<User> getUserById(Long id) throws NotFoundException {
        Optional<User> user;
        if (userRepository.findById(id).isEmpty())
            throw new NotFoundException("Id not found");
        else
            user = userRepository.findById(id);
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUsersByName(String name) {
        return userRepository.getAllByName(name);
    }

    public User createUser(User newUser) {

        //TODO: fac verificari (username ul sa fie unic, email sa fie unic)
        //TODO: sa verific ca parola are un anumit format (lungime,etc)

        User userToBeSaved = User.builder()
                .name(newUser.getName())
                .age(newUser.getAge())
                .email(newUser.getEmail())
                .password(newUser.getPassword())
                .username(newUser.getUsername())
                .address(newUser.getAddress())
                .build();
        userRepository.save(userToBeSaved);
        return userToBeSaved;
    }
}
