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
@Table(name = "Center")

public class Center {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="name")
    private String name;
    @Column(name="city")
    private String city;
    @Column(name="street")
    private String street;
    @Column(name="number")
    private Integer number;
    @Column(name="phone")
    private String phone;
    @Column(name="email")
    private String email;
    @Column(name="capacity")
    private Integer capacity;

    @JsonIgnore
    @OneToMany(mappedBy = "center",orphanRemoval = true, cascade = {CascadeType.ALL})
    List<Pet> petList;

    @OneToMany(mappedBy = "center",orphanRemoval = true, cascade = {CascadeType.ALL})
    List<Employee> employeeList;
}
