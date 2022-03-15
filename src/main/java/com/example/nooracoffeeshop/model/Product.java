package com.example.nooracoffeeshop.model;

import javax.persistence.Entity;
import javax.persistence.Lob;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import org.springframework.data.jpa.domain.AbstractPersistable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product extends AbstractPersistable<Long> {

    private String name;
    private String description;
    private Double price;
    @OneToMany
    private List<Image> images;

    private Long productSold;

    @ManyToOne
    @ToString.Exclude
    private Department department;

    @ManyToOne
    @ToString.Exclude
    private Supplier supplier;

    @ManyToOne
    @ToString.Exclude
    private Manufacturer manufacturer;

}
