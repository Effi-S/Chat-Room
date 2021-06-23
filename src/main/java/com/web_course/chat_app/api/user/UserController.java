package com.web_course.chat_app.api.user;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Optional;


/**
 * The type User controller. <br/>
 * This acts as the endpoint to our User API.
 */
@RestController
@RequestMapping(path="api/v1/users")
public class UserController {
    private final UserService userService;

    /**
     * Instantiates a new User controller.
     *
     * @param userService The user service Bean.
     */
    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Gets a user given a username as a path variable.
     *
     * @param username The username of the User to find.
     * @return The User found.
     */
    @GetMapping("/get/{username}")
    public Optional<User> getUser(@PathVariable("username") String username){
        return userService.getUser(username);
    }

    /**
     * Register a user.
     *
     * @param username The username of the user to register
     * @param request  The request for adding the username to Session Attributes.
     * @param response The response for redirecting to chatroom.
     * @throws IOException the io exception
     */
    @PostMapping("/post")
    public void registerUser(@Payload String username,
                             HttpServletRequest request, HttpServletResponse response) throws IOException {
        userService.addNewUser(new User(username));
        request.getSession().setAttribute("username", username);
        response.sendRedirect("/chatroom");
    }

    /**
     * Get all users list.
     * @return List of all of the Users in the DB.
     */
    @GetMapping("/get/all")
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }
}
