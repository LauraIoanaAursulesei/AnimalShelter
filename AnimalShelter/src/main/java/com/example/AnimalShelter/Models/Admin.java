package com.example.AnimalShelter.Models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "Admin")

public class Admin {

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

}
