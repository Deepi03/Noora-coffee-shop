package com.example.nooracoffeeshop.Repositories;

import com.example.nooracoffeeshop.model.Manufacturer;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ManufacturerRepository  extends JpaRepository<Manufacturer,Long>{
    
}