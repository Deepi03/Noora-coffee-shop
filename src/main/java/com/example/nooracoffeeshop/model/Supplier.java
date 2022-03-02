package com.example.nooracoffeeshop.model;



import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.AbstractPersistable;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Supplier  extends AbstractPersistable<Long>{

    
   private String name;
   private String contactPersonName;
   private String contactPersonEmail;


   @OneToMany(mappedBy = "supplier" )

   private List<Product> products = new ArrayList<>();

   @Override
public String toString() {
    return name;
}
}
