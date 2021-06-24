package com.web_course.chat_app.api.user;

import com.web_course.chat_app.exceptions.UserAlreadyRegisteredException;
import com.web_course.chat_app.exceptions.UserNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;

/**
 * The type User service. <br/>
 * This is the Service layer of user Table in DB.
 */
@Service
public class UserService {

    /** Bean of our User Repository **/
    private final UserRepository userRepository;

    /** Session Info */
    HttpSession session;

    /**
     * Instantiates a new User service. <br/>
     * This is the Service layer of user Table in DB.
     *
     * @param userRepository the user repository
     * @param session - For adding session info to created Users.
     */
    @Autowired
    public UserService(UserRepository userRepository, HttpSession session) {

        this.userRepository = userRepository;
        this.session = session;
    }

    /**
     * Gets a user based on username.
     *
     * @param username the username of the user to get.
     * @return the user
     */
    public Optional<User> getUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }

    /**
     * Gets a user based on sessionId.
     *
     * @param sessionId the sessionId of the user to get.
     * @return the user
     */
    public Optional<User> getUserBySessionId(String sessionId) {
        return userRepository.findUserBySession(sessionId);
    }
    /**
     * Add new user.
     *
     * @param username the user's username
     */
    public void addNewUser(String  username){

        User user = new User(username, session.getId());

        Optional<User> userSearch = userRepository.findUserBySession(user.getSessionId());

        if(userSearch.isPresent())
            throw new UserAlreadyRegisteredException("The chat is open in another Window/Tab.<br/>" +
                    "Please logout and try again");

         userSearch = userRepository.findUserByUsername(user.getUsername());

        if(userSearch.isPresent())
            throw new UserAlreadyRegisteredException(
                    String.format("User %s Already in the chatroom!<br/>" +
                                    "Please log in with a different name.",
                            userSearch.get().getUsername()));

        userRepository.save(user);
    }

    /**
     * Delete a user.
     *
     * @param username the username of the user to delete.
     */
    public void deleteUser(String username) {
        Optional<User> user = userRepository.findUserByUsername(username);
        if(user.isEmpty()){
            throw new UserNotExistException(String.format("User: %s Does not exist", username));
        }
        userRepository.deleteById(user.get().getId());

    }
    /**
     * Gets all of the users.
     *
     * @return List of All the users.
     */
    public List<User> getAllUsers() {
       return userRepository.findAll();
    }

}
