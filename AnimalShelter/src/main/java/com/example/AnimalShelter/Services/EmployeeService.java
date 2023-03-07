package com.example.AnimalShelter.Services;

import com.example.AnimalShelter.Exceptions.InvalidPasswordException;
import com.example.AnimalShelter.Exceptions.NotFoundException;
import com.example.AnimalShelter.Exceptions.AlreadyInUseException;
import com.example.AnimalShelter.Models.Employee;
import com.example.AnimalShelter.Repositories.EmployeeRepository;
import com.example.AnimalShelter.Repositories.CenterRepository;
import com.example.AnimalShelter.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service

public class EmployeeService {

    EmployeeRepository employeeRepository;
    UserRepository userRepository;
    CenterRepository centerRepository;

    public EmployeeService(EmployeeRepository employeeRepository, UserRepository userRepository, CenterRepository centerRepository) {
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
        this.centerRepository = centerRepository;
    }

    public Employee updateEmployee(Employee newEmployee) throws NotFoundException, AlreadyInUseException {
        Employee employee = employeeRepository.findById(newEmployee.getId()).orElseThrow(() -> new NotFoundException("Id not found"));
        if (!isEmployeeUsernameUnique(newEmployee.getUsername()) && !isUserUsernameUnique(newEmployee.getUsername())) {
            throw new AlreadyInUseException("Username already in use");
        }
        if (!isEmployeeEmailUnique(newEmployee.getEmail()) && !isUserEmailUnique(newEmployee.getEmail()) && !isCenterEmailUnique(newEmployee.getEmail())) {
            throw new AlreadyInUseException("Email already in use");
        }

        if (newEmployee.getName() != null)
            employee.setName(newEmployee.getName());
        if (newEmployee.getLocation() != null)
            employee.setLocation(newEmployee.getLocation());
        if (newEmployee.getEmail() != null)
            employee.setEmail(newEmployee.getEmail());
        if (newEmployee.getUsername() != null)
            employee.setUsername(newEmployee.getUsername());
        if (newEmployee.getPassword() != null)
            employee.setPassword(newEmployee.getPassword());

        return employeeRepository.save(employee);
    }

    public void deleteEmployee(Long id) throws NotFoundException {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new NotFoundException("Id not found"));
        employeeRepository.delete(employee);
    }

    public Employee getEmployeeById(Long id) throws NotFoundException {
        Employee employee = employeeRepository.findById(id).orElseThrow(() -> new NotFoundException("Id not found"));
        return employee;
    }

    public Employee getEmployeeByUsername(String username) throws NotFoundException {
        Employee employee = employeeRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("Username not found"));
        return employee;
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.findAll();
    }

    public boolean isEmployeeEmailUnique(String email) {
        return employeeRepository.findByEmail(email).isEmpty();
    }

    public boolean isUserEmailUnique(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }

    public boolean isCenterEmailUnique(String email) {
        return centerRepository.findByEmail(email).isEmpty();
    }

    public boolean isEmployeeUsernameUnique(String username) {
        return employeeRepository.findByUsername(username).isEmpty();
    }

    public boolean isUserUsernameUnique(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }

    private static final String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[!@#&()–[{}]:;',?/*~$^+=<>]).{8,20}$";
    private static final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);

    public static boolean isEmployeePasswordValid(final String password) {
        Matcher matcher = pattern.matcher(password);
        return matcher.matches();
    }

    public Employee createEmployee(Employee newEmployee) throws AlreadyInUseException, InvalidPasswordException {
        if (!isEmployeeUsernameUnique(newEmployee.getUsername()) && !isUserUsernameUnique(newEmployee.getUsername())) {
            throw new AlreadyInUseException("Username already in use");
        }
        if (!isEmployeeEmailUnique(newEmployee.getEmail()) && !isUserEmailUnique(newEmployee.getEmail()) && !isCenterEmailUnique(newEmployee.getEmail())) {
            throw new AlreadyInUseException("Email already in use");
        }
        if (!isEmployeePasswordValid(newEmployee.getPassword())) {
            throw new InvalidPasswordException("Invalid password");
        }

        Employee employeeToBeSaved = Employee.builder()
                .name(newEmployee.getName())
                .location(newEmployee.getLocation())
                .email(newEmployee.getEmail())
                .username(newEmployee.getUsername())
                .password(newEmployee.getPassword())
                .build();
        employeeRepository.save(employeeToBeSaved);
        return employeeToBeSaved;
    }
}
