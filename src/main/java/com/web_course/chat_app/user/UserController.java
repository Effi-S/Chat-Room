package com.web_course.chat_app.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path="api/v1/users")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/get/{sess}")
    public User getUsers(@PathVariable("sess") String id){
//        return new User(1L, "John", "Password");
        return userService.getUser(id);
    }

    @PostMapping("/post")
    public void addNewUser(@RequestBody User user){
        userService.addNewUser(user);
    }

    @DeleteMapping(path = "delete/{uid}")
    public void deleteUser(@PathVariable("uid") Long id){
        userService.deleteUser(id);
    }
}
