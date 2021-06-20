package com.web_course.chat_app.api.user;

import com.web_course.chat_app.exceptions.UserAlreadyRegisteredException;
import com.web_course.chat_app.exceptions.UserNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public Optional<User> getUser(String username) {
        return userRepository.findUser(username);
    }

    public void addNewUser(User user){
        Optional<User> userSearch =
                userRepository.findUser(user.getUsername());

        if(userSearch.isPresent()){
                throw new UserAlreadyRegisteredException("User " +
                        userSearch.get().getUsername() + "Already in chatroom!");
        }
        userRepository.save(user);
    }

    public void deleteUser(String username) {
        Optional<User> user = userRepository.findUser(username);
        if(user.isEmpty()){
            throw new UserNotExistException("User: " + username + " Does not exist");
        }
        userRepository.deleteById(user.get().getId());

    }

    public List<User> getAllUsers() {
       return userRepository.findAll();
    }
}
