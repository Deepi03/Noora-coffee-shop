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

// import java.util.Arrays;

// import com.example.nooracoffeeshop.model.User;
// import com.example.nooracoffeeshop.Repositories.UserRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.security.core.authority.SimpleGrantedAuthority;
// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import
// org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// @Service
// public class CustomerUserDetailsService implements UserDetailsService {

// @Autowired
// private UserRepository userRepository;

// @Override
// public UserDetails loadUserByUsername(String username) throws
// UsernameNotFoundException {
// User customer = userRepository.findByUsername(username).get();
// if (customer == null) {
// throw new UsernameNotFoundException("No such user " + username);
// }

// // return new org.springframework.security.core.userdetails.User(
// // customer.getUsername(),
// // customer.getPassword(),
// // true,
// // true,
// // true,
// // true,
// // Arrays.asList(new SimpleGrantedAuthority("USER"), new
// // SimpleGrantedAuthority("ADMIN")));

// return new MyUserDetails(customer);
// }

// // public boolean isValidUser(String email, String password) {
// // User customer = userRepository.findByEmail(email);
// // System.out.println("******************" + customer + "************");
// // if (customer == null) {
// // return false;
// // } else {
// // if (customer.getPassword().equals(password)) {
// // return true;
// // }
// // return false;
// // }

// // }
// }

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
