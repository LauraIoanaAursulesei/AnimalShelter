package com.example.AnimalShelter.Controllers;

import com.example.AnimalShelter.Exceptions.NotFoundException;
import com.example.AnimalShelter.Exceptions.AlreadyInUseException;
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

    @PutMapping()
    public User updateUser(@RequestBody User newUser) throws NotFoundException, AlreadyInUseException {
        return userService.updateUser(newUser);
    }

    @DeleteMapping()
    public void deleteUser(Long id) throws NotFoundException {
        userService.deleteUser(id);
    }

    @PostMapping()
    public User createUser(@RequestBody User newUser) throws AlreadyInUseException {
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

    @GetMapping("/userByUsername")
    public User getUserByUsername(@RequestParam String username) throws NotFoundException {
        return userService.getUserByUsername(username);
    }
}
