package com.example.AnimalShelter.Models;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Builder
@Table(name = "Pet")

public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;
    @Column(name = "age")
    private int age;
    @Column(name = "location")
    private String location;
    @Column(name = "race")
    private String race;
    @Column(name = "size")
    private String size;
    @Column(name = "sex")
    private String sex;
    @Column(name = "behavior")
    private String behavior;
    @Column(name = "vaccine")
    private Boolean vaccine;

}
