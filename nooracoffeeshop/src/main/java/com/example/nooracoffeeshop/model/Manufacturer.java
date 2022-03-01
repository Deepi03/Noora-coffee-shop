package com.example.nooracoffeeshop.model;




import java.util.ArrayList;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import lombok.Data;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor



public class Manufacturer extends AbstractPersistable<Long> {

    private String name;
    private String url;

    @OneToMany(mappedBy = "manufacturer")

    private List<Product> products = new ArrayList<>();

    @Override
public String toString() {
    return name;
}
}
