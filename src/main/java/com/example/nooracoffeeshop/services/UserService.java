// package com.example.nooracoffeeshop.services;

// import com.example.nooracoffeeshop.model.User;
// import com.example.nooracoffeeshop.Repositories.UserRepository;

// import org.springframework.beans.factory.annotation.Autowired;
// // import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// @Service
// public class UserService {

// @Autowired
// private UserRepository userRepository;

// // @Autowired
// // private PasswordEncoder passwordEncoder;

// public User getUser(String email) {
// return userRepository.findByEmail(email);
// }

// public User getUserById(Long id) {
// return userRepository.getById(id);
// }

// public void addUser(String name, String userName, String address, String
// phoneNumber, String email, String password,
// Boolean isAdmin) {
// User user = new User();
// user.setName(name);
// user.setUsername(userName);
// user.setAddress(address);
// user.setPhoneNumber(phoneNumber);
// user.setEmail(email);
// user.setPassword(password);
// user.setIsAdmin(isAdmin);
// System.out.println(user.toString());
// userRepository.save(user);
// }

// public void updateUser(Long id, String name, String userName, String address,
// String phoneNumber, String email) {
// User user = userRepository.getById(id);
// user.setName(name);
// user.setUsername(userName);
// user.setAddress(address);
// user.setPhoneNumber(phoneNumber);
// user.setEmail(email);

// userRepository.save(user);
// }

// public void resetPassword(String email, String password) {
// User user = userRepository.findByEmail(email);
// user.setPassword(passwordEncoder.encode(password));
// userRepository.save(user);
// }

// public void deleteUser(Long id) {
// userRepository.deleteById(id);
// }

// }
