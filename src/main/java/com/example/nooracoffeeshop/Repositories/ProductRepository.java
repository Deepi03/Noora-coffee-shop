package com.example.nooracoffeeshop.Repositories;

import java.util.Collection;
import java.util.List;

import com.example.nooracoffeeshop.model.Product;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findByDepartment_idIn(Collection<Long> products);

    @Query("SELECT product FROM Product product WHERE lower(product.name) LIKE %?1%")
    List<Product> findByNameContainingIgnoreCase(String keyword);

}
