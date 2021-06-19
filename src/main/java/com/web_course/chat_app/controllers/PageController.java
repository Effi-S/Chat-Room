package com.web_course.chat_app.controllers;
import com.web_course.chat_app.api.user.User;
import com.web_course.chat_app.api.user.UserService;
import com.web_course.chat_app.exceptions.UserAlreadyRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;


@Controller
public class PageController {

    @Autowired
    public PageController(UserService userService) {
        this.userService = userService;
    }

    UserService userService;

    @RequestMapping("/")
    public String landingPage(){
        return "/login";
    }

    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping("/chatroom")
    public String connectToChat(Model model, @Payload String username){
        if(userService.getUser(username).isPresent()){
            throw new UserAlreadyRegisteredException("User " + username + "Already in chatroom!");
        }

        userService.addNewUser(new User(username));
        model.addAttribute("username", username);
        return "chat-client";
    }
}
