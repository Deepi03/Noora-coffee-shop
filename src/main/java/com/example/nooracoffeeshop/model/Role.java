package com.example.nooracoffeeshop.model;

import javax.persistence.*;
import java.util.List;
import lombok.Data;
import javax.validation.constraints.NotEmpty;

@Entity
@Data
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(nullable = false, unique = true)
    @NotEmpty
    private String name;

    @ManyToMany(mappedBy = "roles")
    private List<User> users;

}
