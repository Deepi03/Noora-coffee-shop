package com.example.nooracoffeeshop.Repositories;

import java.util.Optional;

import com.example.nooracoffeeshop.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findUserByEmail(String email);

}
