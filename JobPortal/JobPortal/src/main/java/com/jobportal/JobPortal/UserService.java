package com.jobportal.JobPortal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    //  Signup
    public String signup(User user) {
        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            return "Email already exists!";
        }
        userRepository.save(user);
        return "User registered successfully!";
    }

    //LOGIN
    public boolean login(String email, String password) {
        return userRepository.findByEmail(email)
                .filter(user -> user.getPassword().equals(password)) // check password if user exists
                .isPresent(); // true if match found, false otherwise
    }

    //Create User
    public String createUser(User user){
        userRepository.save(user);
        return "User Created Successfully!";
    }

    //Read by user id
    public String getUserById(Long id) {
        return userRepository.findById(id)
                .map(User::toString)                 // if found → call toString()
                .orElse("User not found with id " + id); // if not found
    }

   // Read all User
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }


    // Delete
    public String deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return "User deleted successfully!";
        }
        return "User not found!";
    }
}
