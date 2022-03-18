package com.example.nooracoffeeshop.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Department extends AbstractPersistable<Long> {

    private String name;
    private long parentId;

    @OneToMany(mappedBy = "department")
    private List<Product> products = new ArrayList<>();

    @Override
    public String toString() {
        return name;
    }

}
