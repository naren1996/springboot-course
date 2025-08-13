package com.narensoft.user_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/msg")
    public String msg(){
        return "Test API";
    }

    @GetMapping("/read/all")
    public String readAllUser() {
        return userService.readAllUser();
    }

    @PostMapping("/create")
    public String createUser(@RequestBody User user) {

        String newUser = userService.createUser(user);
        return newUser;
    }

    @PutMapping(value = "/update/{id}")
    public String updateUser(@PathVariable Long id, @RequestBody User user) {

        String updatedUser = userService.updateUser(id, user);
        return updatedUser;
    }

    @DeleteMapping("/delete/{id}")
    public void deleteUser() {
        userService.deleteUser();
    }
}
