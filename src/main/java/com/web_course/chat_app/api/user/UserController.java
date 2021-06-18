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

    @GetMapping("/get/{sess}")
    public Optional<User> getUser(@PathVariable("sess") String id){
//        return new User(1L, "John", "Password");
        return userService.getUserBySession(id);
    }

    @PostMapping("/post")
    public void registerUser(@RequestBody User user){
        userService.addNewUser(user);
    }

    @DeleteMapping(path = "delete/{uid}")
    public void deleteUser(@PathVariable("uid") Long id){
        userService.deleteUser(id);
    }
}
