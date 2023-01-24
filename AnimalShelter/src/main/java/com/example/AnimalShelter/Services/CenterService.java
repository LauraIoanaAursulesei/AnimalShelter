package com.example.AnimalShelter.Services;

import com.example.AnimalShelter.Exceptions.NotFoundException;
import com.example.AnimalShelter.Models.Center;
import com.example.AnimalShelter.Repositories.CenterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CenterService {

    CenterRepository centerRepository;

    public CenterService(CenterRepository centerRepository) {
        this.centerRepository = centerRepository;
    }

    public Optional<Center> getCenterById(int id) throws NotFoundException {
        Optional<Center> center;
        if (centerRepository.getById(id).isEmpty())
            throw new NotFoundException("Id not found");
        else
            center = centerRepository.getById(id);
        return center;
    }

    public List<Center> getAllCenters(){
        return centerRepository.findAll();
    }

    public Center createCenter(Center newCenter){

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

       /* Center a = new Center();
        a.setName("Happy Pet Bucharest");
        a.setCity("Bucuresti");
        a.setStreet("Soseaua Pantelimon");
        a.setNumber(362);
        a.setPhone(867436786);
        a.setEmail("happypetbucharest@gmail.com");
        a.setCapacity(20);*/
        return centerToBeSaved;
    }
}
