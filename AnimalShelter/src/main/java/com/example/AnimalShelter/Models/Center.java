package com.example.AnimalShelter.Models;

import jakarta.persistence.*;
import lombok.*;

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
}
