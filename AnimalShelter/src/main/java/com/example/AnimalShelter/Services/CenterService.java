package com.example.AnimalShelter.Services;

import com.example.AnimalShelter.Exceptions.AlreadyInUseException;
import com.example.AnimalShelter.Exceptions.NotFoundException;
import com.example.AnimalShelter.Models.Center;
import com.example.AnimalShelter.Repositories.EmployeeRepository;
import com.example.AnimalShelter.Repositories.CenterRepository;
import com.example.AnimalShelter.Repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CenterService {

    CenterRepository centerRepository;
    EmployeeRepository employeeRepository;
    UserRepository userRepository;

    public CenterService(CenterRepository centerRepository, EmployeeRepository employeeRepository, UserRepository userRepository) {
        this.centerRepository = centerRepository;
        this.employeeRepository = employeeRepository;
        this.userRepository = userRepository;
    }

    public Center updateCenter(Center newCenter) throws NotFoundException, AlreadyInUseException {
        Center center = centerRepository.findById(newCenter.getId()).orElseThrow(() -> new NotFoundException("Id not found"));
        if (!isCenterEmailUnique(newCenter.getEmail()) && !isAdminEmailUnique(newCenter.getEmail()) && !isUserEmailUnique(newCenter.getEmail())) {
            throw new AlreadyInUseException("Email already in use");
        }

        if (newCenter.getName() != null)
            center.setName(newCenter.getName());
        if (newCenter.getCity() != null)
            center.setCity(newCenter.getCity());
        if (newCenter.getStreet() != null)
            center.setStreet(newCenter.getStreet());
        if (newCenter.getNumber() != null)
            center.setNumber(newCenter.getNumber());
        if (newCenter.getPhone() != null)
            center.setPhone(newCenter.getPhone());
        if (newCenter.getEmail() != null)
            center.setEmail(newCenter.getEmail());
        if (newCenter.getCapacity() != null)
            center.setCapacity(newCenter.getCapacity());

        return centerRepository.save(center);
    }

    public void deleteCenter(Long id) throws NotFoundException {
        Center center = centerRepository.findById(id).orElseThrow(() -> new NotFoundException("Id not found"));
        centerRepository.delete(center);
    }

    public Optional<Center> getCenterById(Long id) throws NotFoundException {
        Optional<Center> center;
        if (centerRepository.findById(id).isEmpty())
            throw new NotFoundException("Id not found");
        else
            center = centerRepository.findById(id);
        return center;
    }

    public boolean isCenterEmailUnique(String email) {
        return centerRepository.findByEmail(email).isEmpty();
    }

    public boolean isUserEmailUnique(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }

    public boolean isAdminEmailUnique(String email) {
        return employeeRepository.findByEmail(email).isEmpty();
    }

    public List<Center> getAllCenters() {
        return centerRepository.findAll();
    }

    public Center createCenter(Center newCenter) throws AlreadyInUseException {
        if (!isCenterEmailUnique(newCenter.getEmail()) && !isAdminEmailUnique(newCenter.getEmail()) && !isUserEmailUnique(newCenter.getEmail())) {
            throw new AlreadyInUseException("Email already in use");
        }

        Center centerToBeSaved = Center.builder()
                .name(newCenter.getName())
                .city(newCenter.getCity())
                .street(newCenter.getStreet())
                .number(newCenter.getNumber())
                .phone(newCenter.getPhone())
                .email(newCenter.getEmail())
                .capacity(newCenter.getCapacity())
                .build();
        centerRepository.save(centerToBeSaved);
        return centerToBeSaved;
    }
}
