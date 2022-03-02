package com.example.nooracoffeeshop.Repositories;

import java.util.Collection;
import java.util.List;

import com.example.nooracoffeeshop.model.Product;


import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
    List<Product> findByDepartment_idIn(Collection<Long> products);

}
