package com.web_course.chat_app.controllers;



import com.web_course.chat_app.exceptions.UserNotRegisteredException;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;




@Controller
public class PageController {
    @RequestMapping("/login")
    public String loginPage(){
        return "login";
    }

    @RequestMapping("/")
    public String landingPage(){
        return "chat-client";
    }
}
