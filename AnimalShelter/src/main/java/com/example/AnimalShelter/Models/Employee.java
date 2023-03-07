package com.example.AnimalShelter.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "Employee")

public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name= "name")
    private String name;
    @Column(name= "location")
    private String location;
    @Column(name= "email")
    private String email;
    @Column(name= "username")
    private String username;
    @Column(name= "password")
    private String password;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private Center center;

    @OneToMany(mappedBy = "employee",orphanRemoval = true,cascade = {CascadeType.ALL})
    List<EmployeeResponsibility> employeeResponsibilityList;
}
