package com.example.AnimalShelter.Controllers;

import com.example.AnimalShelter.Exceptions.NotFoundException;
import com.example.AnimalShelter.Models.User;
import com.example.AnimalShelter.Services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/user")

public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @DeleteMapping()
    public void deleteUser(Long id) throws NotFoundException {
        userService.deleteUser(id);
    }

    @PostMapping()
    public User createUser(@RequestBody User newUser) {
        return userService.createUser(newUser);
    }

    @GetMapping("/userById")
    public Optional<User> getUserById(@RequestParam Long id) throws NotFoundException {
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
