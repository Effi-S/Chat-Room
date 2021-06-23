package com.web_course.chat_app.api.user;

import com.web_course.chat_app.exceptions.UserAlreadyRegisteredException;
import com.web_course.chat_app.exceptions.UserNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    /**
     * Instantiates a new User service. <br/>
     * This is the Service layer of user Table in DB.
     *
     * @param userRepository the user repository
     */
    @Autowired
    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    /**
     * Gets a user.
     *
     * @param username the username of the user to get.
     * @return the user
     */
    public Optional<User> getUser(String username) {
        return userRepository.findUser(username);
    }

    /**
     * Add new user.
     *
     * @param user the user
     */
    public void addNewUser(User user){
        Optional<User> userSearch =
                userRepository.findUser(user.getUsername());

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
        Optional<User> user = userRepository.findUser(username);
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
