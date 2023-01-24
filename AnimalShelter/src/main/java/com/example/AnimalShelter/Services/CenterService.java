package com.example.AnimalShelter.Services;

import com.example.AnimalShelter.Models.Center;
import com.example.AnimalShelter.Repositories.CenterRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
//modificare
public class CenterService {

    CenterRepository centerRepository;

    public CenterService(CenterRepository centerRepository) {
        this.centerRepository = centerRepository;
    }

    public List<Center> getCentersbyId(int id){
        return centerRepository.getAllById(id);
    }

    public List<Center> getAllCenters(){
        return centerRepository.findAll();
    }

    public Center createCenter(){
        Center a = new Center();
        a.setName("Happy Pet Bucharest");
        a.setCity("Bucuresti");
        a.setStreet("Soseaua Pantelimon");
        a.setNumber(362);
        a.setPhone(867436786);
        a.setEmail("happypetbucharest@gmail.com");
        a.setCapacity(20);
        return a;
    }
}
