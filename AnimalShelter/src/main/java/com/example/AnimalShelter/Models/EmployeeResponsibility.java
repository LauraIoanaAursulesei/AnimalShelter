package com.example.AnimalShelter.Models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "employeeResponsibilities")

public class EmployeeResponsibility {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = {CascadeType.MERGE})
    private Employee employee;
    @ManyToOne(cascade = {CascadeType.MERGE})
    private Responsibility responsibility;
}
