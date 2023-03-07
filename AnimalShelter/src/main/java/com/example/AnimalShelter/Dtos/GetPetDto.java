package com.example.AnimalShelter.Dtos;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetPetDto {

    private String name;
    private Integer age;
    private String location;
    private String race;
    private String size;
    private String sex;
    private String behavior;
    private Boolean vaccine;
    private String centerName;
}
