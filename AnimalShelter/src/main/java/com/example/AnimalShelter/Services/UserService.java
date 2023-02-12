package com.example.AnimalShelter.Services;

import com.example.AnimalShelter.Exceptions.InvalidPasswordException;
import com.example.AnimalShelter.Exceptions.NotFoundException;
import com.example.AnimalShelter.Exceptions.AlreadyInUseException;
import com.example.AnimalShelter.Models.User;
import com.example.AnimalShelter.Repositories.AdminRepository;
import com.example.AnimalShelter.Repositories.CenterRepository;
import com.example.AnimalShelter.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service

public class UserService {

    UserRepository userRepository;
    AdminRepository adminRepository;
    CenterRepository centerRepository;

    public UserService(UserRepository userRepository, AdminRepository adminRepository, CenterRepository centerRepository) {
        this.userRepository = userRepository;
        this.adminRepository = adminRepository;
        this.centerRepository = centerRepository;
    }

    public User updateUser(User newUser) throws NotFoundException, AlreadyInUseException {
        User user = userRepository.findById(newUser.getId()).orElseThrow(() -> new NotFoundException("Id not found"));
        if (!isUserUsernameUnique(newUser.getUsername()) && !isAdminUsernameUnique(newUser.getUsername())) {
            throw new AlreadyInUseException("Username already in use");
        }
        if (!isUserEmailUnique(newUser.getEmail()) && !isAdminEmailUnique(newUser.getEmail()) && !isCenterEmailUnique(newUser.getEmail())) {
            throw new AlreadyInUseException("Email already in use");
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

    public List<User> getAllUsers() throws NotFoundException {
        List<User> users = userRepository.findAll();
        if (users.isEmpty())
            throw new NotFoundException("There are no users to display");
        return users;
    }

    public List<User> getUsersByName(String name) throws NotFoundException {
        List<User> users = userRepository.getAllByName(name);
        if (users.isEmpty())
            throw new NotFoundException("There are no users with this name");
        return users;
    }

    public boolean isUserEmailUnique(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }

    public boolean isAdminEmailUnique(String email) {
        return adminRepository.findByEmail(email).isEmpty();
    }

    public boolean isCenterEmailUnique(String email) {
        return centerRepository.findByEmail(email).isEmpty();
    }

    public boolean isUserUsernameUnique(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }

    public boolean isAdminUsernameUnique(String username) {
        return adminRepository.findByUsername(username).isEmpty();
    }

    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public static boolean isUserPasswordValid(final String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public User createUser(User newUser) throws AlreadyInUseException, InvalidPasswordException {
        if (!isUserUsernameUnique(newUser.getUsername()) && !isAdminUsernameUnique(newUser.getUsername())) {
            throw new AlreadyInUseException("Username already in use");
        }
        if (!isUserEmailUnique(newUser.getEmail()) && !isAdminEmailUnique(newUser.getEmail()) && !isCenterEmailUnique(newUser.getEmail())) {
            throw new AlreadyInUseException("Email already in use");
        }
        if (!isUserPasswordValid(newUser.getPassword())) {
            throw new InvalidPasswordException("Invalid password");
        }

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
