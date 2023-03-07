package com.example.AnimalShelter.Controllers;

import com.example.AnimalShelter.Exceptions.InvalidPasswordException;
import com.example.AnimalShelter.Exceptions.NotFoundException;
import com.example.AnimalShelter.Exceptions.AlreadyInUseException;
import com.example.AnimalShelter.Models.Employee;
import com.example.AnimalShelter.Services.EmployeeService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")

public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @PutMapping()
    public Employee updateEmployee(@RequestBody Employee newEmployee) throws NotFoundException, AlreadyInUseException {
        return employeeService.updateEmployee(newEmployee);
    }

    @DeleteMapping()
    public void deleteEmployee(Long id) throws NotFoundException {
        employeeService.deleteEmployee(id);
    }

    @PostMapping()
    public Employee createEmployee(@RequestBody Employee newEmployee) throws AlreadyInUseException, InvalidPasswordException {
        return employeeService.createEmployee(newEmployee);
    }

    @GetMapping("/employeeById")
    public Employee getEmployeeById(@RequestParam Long id) throws NotFoundException {
        return employeeService.getEmployeeById(id);
    }

    @GetMapping("/employeeByUsername")
    public Employee getEmployeeByUsername(@RequestParam String username) throws NotFoundException {
        return employeeService.getEmployeeByUsername(username);
    }

    @GetMapping("/allEmployees")
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
}
