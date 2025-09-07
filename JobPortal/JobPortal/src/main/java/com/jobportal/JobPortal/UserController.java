package com.jobportal.JobPortal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public String signup(@RequestBody User user){
        return userService.signup(user);
    }

    @PostMapping("/login")
    public String login(@RequestParam String email, @RequestParam String password) {
        boolean success = userService.login(email, password);
        return success ? "Login Successful!" : "Invalid Credentials!";
    }

    //  Get All Users
    @GetMapping("/all")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    // READ User by id
    @GetMapping("/get/{id}")
    public String getUser(@PathVariable Long id){
        return userService.getUserById(id);

    }
    // Delete User by ID
    @DeleteMapping("/delete/{id}")
    public String deleteUser(@PathVariable Long id) {
        return userService.deleteUser(id);
    }
}
