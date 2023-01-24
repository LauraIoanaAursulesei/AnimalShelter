package com.example.AnimalShelter.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
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
    private int number;
    @Column(name="phone")
    private int phone;
    @Column(name="email")
    private String email;
    @Column(name="capacity")
    private int capacity;
}
