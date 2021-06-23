package com.web_course.chat_app.controllers;
import com.web_course.chat_app.api.message.MessageService;
import com.web_course.chat_app.api.user.User;
import com.web_course.chat_app.api.user.UserService;
import com.web_course.chat_app.exceptions.UserAlreadyRegisteredException;
import com.web_course.chat_app.exceptions.UserNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;


@Controller
public class PageController {

    MessageService messageService;
    UserService userService;

    @Autowired
    public PageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    @RequestMapping("/")
    public String landingPage(){
        return "/login";
    }

    @RequestMapping("/login")
    public String loginPage(){
        try {
            return "login";
        } catch (UserAlreadyRegisteredException ex){
            throw ex;
        }
    }

    @RequestMapping("/chatroom")
    public String connectToChat(Model model,
                                HttpServletRequest request){

        // --1-- If username is empty (not in session) throw UserNotExistException
        String username = (String) request.getSession().getAttribute("username");
        if(username == null || username.isBlank()){
            throw new UserNotExistException("No Username entered.<br />Please Log in to Continue.. ");
        }

        //--2-- Supply model attributes
        model.addAttribute("username", username);
        model.addAttribute("messages",  messageService.getLast5Messages());
        model.addAttribute("users", userService.getAllUsers());
        return "chat-client";
    }
}
