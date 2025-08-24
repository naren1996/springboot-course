package com.narensoft.user_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private UserRepository userRepository;

    UserService(@Autowired UserRepository userRepo) {
        this.userRepository = userRepo;
    }

//    public String readAllUser() {
//        Optional<> = userRepository.getById(1L);
//    }

    public String getUserById(Long id) {
        User user = userRepository.getById(id);
        return user.toString();
    }

    public String getUserByName(String name) {

        Optional<User> optional = userRepository.findByName(name);
//        if(optional.isPresent()) {
            User user = optional.get();
            return user.toString();
//        }
//        return "No Data Found";
    }

    public String createUser(User user) {
        //TO-DO
        return null;
    }


    public String updateUser(Long id, User user) {
       //TO-DO
        return null;
    }

    public void deleteUser() {
        //TO-DO
    }
}
