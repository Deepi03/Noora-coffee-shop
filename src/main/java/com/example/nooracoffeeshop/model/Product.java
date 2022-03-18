package com.example.nooracoffeeshop.model;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
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
import javax.persistence.OneToOne;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product extends AbstractPersistable<Long> {

    private String name;
    private String description;
    private Double price;

    @ManyToOne
    @JoinColumn(name = "department_id", referencedColumnName = "id")
    @ToString.Exclude
    private Department department;

    @ManyToOne
    @ToString.Exclude
    private Supplier supplier;

    @ManyToOne
    @ToString.Exclude
    private Manufacturer manufacturer;
    private String imageName;
}
