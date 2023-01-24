package com.example.AnimalShelter.Controllers;

import com.example.AnimalShelter.Exceptions.NotFoundException;
import com.example.AnimalShelter.Models.Pet;
import com.example.AnimalShelter.Models.User;
import com.example.AnimalShelter.Services.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")

public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping()
    public User createUser() {
        return userService.createUser();
    }

    @GetMapping("/userById")
    public Optional<User> getUserById(@RequestParam int id) throws NotFoundException {
        return userService.getUserById(id);
    }

    @GetMapping("/allUsers")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @GetMapping("/usersByName")
    public List<User> getUsersByName(@RequestParam String name) {
        return userService.getUsersByName(name);
    }
}
