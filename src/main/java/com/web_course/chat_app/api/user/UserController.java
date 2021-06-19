package com.web_course.chat_app.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@RestController
@RequestMapping(path="api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get/{username}")
    public Optional<User> getUser(@PathVariable("username") String username){
        return userService.getUser(username);
    }

    @PostMapping("/post")
    public void registerUser(@RequestBody User user){
        userService.addNewUser(user);
    }

}
