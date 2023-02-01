package com.example.AnimalShelter.Services;

import com.example.AnimalShelter.Exceptions.NotFoundException;
import com.example.AnimalShelter.Exceptions.UsernameAlreadyInUseException;
import com.example.AnimalShelter.Models.User;
import com.example.AnimalShelter.Repositories.AdminRepository;
import com.example.AnimalShelter.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service

public class UserService {

    UserRepository userRepository;
    AdminRepository adminRepository;

    public UserService(UserRepository userRepository, AdminRepository adminRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
    }

    public User updateUser(User newUser) throws NotFoundException, UsernameAlreadyInUseException {
        User user = userRepository.findById(newUser.getId()).orElseThrow(() -> new NotFoundException("Id not found"));
        if (!isUserUsernameUnique(newUser.getUsername()) && !isAdminUsernameUnique(newUser.getUsername())) {
            throw new UsernameAlreadyInUseException("Username already in use");
        }

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

    public User getUserByUsername(String username) throws NotFoundException {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("Username not found"));
        return user;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public List<User> getUsersByName(String name) {
        return userRepository.getAllByName(name);
    }

    public boolean isUserUsernameUnique(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }

    public boolean isAdminUsernameUnique(String username) {
        return adminRepository.findByUsername(username).isEmpty();
    }

    public User createUser(User newUser) throws UsernameAlreadyInUseException {
        if (!isUserUsernameUnique(newUser.getUsername()) && !isAdminUsernameUnique(newUser.getUsername())) {
            throw new UsernameAlreadyInUseException("Username already in use");
        }

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
