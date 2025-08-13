package com.narensoft.user_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserRepository userRepository;

    UserService(UserRepository userRepo) {
        this.userRepository = userRepo;
    }

    public String readAllUser() {

    }

    public String createUser(User user) {
        //TO-DO
    }


    public String updateUser(Long id, User user) {
       //TO-DO
    }

    public void deleteUser() {
        //TO-DO
    }
}
