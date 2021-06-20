package com.web_course.chat_app.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
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
    public void registerUser(@Payload String username, HttpServletRequest request, HttpServletResponse response) throws IOException {
        userService.addNewUser(new User(username));
        request.getSession().setAttribute("username", username);
        response.sendRedirect("/chatroom");
    }

    @GetMapping("/get/all")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
}
