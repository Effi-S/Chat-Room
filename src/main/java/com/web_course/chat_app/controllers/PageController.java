package com.web_course.chat_app.controllers;
import com.web_course.chat_app.api.message.MessageService;
import com.web_course.chat_app.api.user.UserService;
import com.web_course.chat_app.exceptions.UserAlreadyRegisteredException;
import com.web_course.chat_app.exceptions.UserNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


/**
 * The Page controller. <br/>
 * This controls the Request mapping of our app.
 *
 */
@Controller
public class PageController {

    /** The Message service.*/
    MessageService messageService;
    /** The User service.*/
    UserService userService;

    /**
     * Instantiates a new Page controller.
     *
     * @param messageService The message service.
     * @param userService    The user service.
     */
    @Autowired
    public PageController(MessageService messageService, UserService userService) {
        this.messageService = messageService;
        this.userService = userService;
    }

    /**
     * Map Landing page. Redirects to login.
     *
     * @return "/login"
     */
    @RequestMapping(value = {"/", "/login"})
    public String landingPage(){
        return "/login";
    }

    /**
     * Connect to chatroom.
     *
     * @param model   The model is used for adding variables to Thymeleaf.
     * @param request The request is used for getting the username from session attributes.
     * @return The chat-client View.
     */
    @RequestMapping("/chatroom")
    public String connectToChat(Model model,
                                HttpServletRequest request) throws IOException {

        // --1-- If username is empty (not in session) throw UserNotExistException
        String username = (String) request.getSession().getAttribute("username");
        if(username == null || username.isBlank()) {
            throw new UserNotExistException("No Username entered.<br />Please Log in to Continue.. ");
        }

        //--2-- Supply model attributes
        model.addAttribute("username", username);
        model.addAttribute("messages",  messageService.getLast5Messages());
        model.addAttribute("users", userService.getAllUsers());
        return "chat-client";
    }

    /**
     * Logout endpoint.
     * Removes "username" from session and returns to login page.
     *
     * @return login View.
     */
    @RequestMapping("/logout")
    public String logOut(HttpServletRequest request){
        String username = (String) request.getSession().getAttribute("username");
        request.getSession().removeAttribute("username");
        userService.deleteUser(username);
        return "login";
    }

}
