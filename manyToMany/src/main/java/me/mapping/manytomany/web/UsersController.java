package me.mapping.manytomany.web;

import me.mapping.manytomany.entities.User;
import me.mapping.manytomany.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UsersController {
    @Autowired
    private UserService userService;
    @GetMapping("/users/{username}")
    public User user(@PathVariable String username){
        User  user = userService.findUserByUserName(username);
        return user;
    }

}
