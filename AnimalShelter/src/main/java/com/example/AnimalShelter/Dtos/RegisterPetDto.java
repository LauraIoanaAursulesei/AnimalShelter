package com.example.AnimalShelter.Dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterPetDto {
    private String name;
    private Integer age;
    private String location;
    private String race;
    private String size;
    private String sex;
    private String behavior;
    private Boolean vaccine;
    private Long centerId;
}
