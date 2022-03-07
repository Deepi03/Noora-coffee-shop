package com.example.nooracoffeeshop.services;

import java.util.List;

import com.example.nooracoffeeshop.Repositories.ManufacturerRepository;
import com.example.nooracoffeeshop.model.Manufacturer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ManufacturerService {

    @Autowired 
    private ManufacturerRepository manufacturerRepository;


    public List<Manufacturer> listAll(){

        return manufacturerRepository.findAll();

    }


    public Manufacturer getManufacturer(Long id){

        return manufacturerRepository.getById(id);

    }

    public void addManufacturer(String manufacturerName,String manufacturerUrl){

        Manufacturer manufacturer = new Manufacturer();
        manufacturer.setName(manufacturerName);
        manufacturer.setUrl(manufacturerUrl);
        manufacturerRepository.save(manufacturer);

    }


    public void updateManufacturer(Long id,String manufacturerName,String manufacturerUrl){
        Manufacturer existingManufacturer = manufacturerRepository.getById(id);
        existingManufacturer.setName(manufacturerName);
        existingManufacturer.setUrl(manufacturerUrl);
        manufacturerRepository.save(existingManufacturer);
    }

    public void deleteManufacturer(Long id){
        manufacturerRepository.deleteById(id);
    }
     
}
