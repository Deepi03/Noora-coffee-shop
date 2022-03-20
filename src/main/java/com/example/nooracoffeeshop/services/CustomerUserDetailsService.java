package com.example.nooracoffeeshop.services;

import java.util.Optional;

import com.example.nooracoffeeshop.Repositories.UserRepository;
import com.example.nooracoffeeshop.model.CustomerUserDetail;
import com.example.nooracoffeeshop.model.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomerUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(email);
        user.orElseThrow(() -> new UsernameNotFoundException("User not found!"));
        return user.map(CustomerUserDetail::new).get();

    }

}
