package com.web_course.chat_app.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public User getUser(String session) {
        User user = userRepository.findUserBySession(session);
        if(user == null){
            throw new IllegalStateException("User Does not exists");
        }
        return user;
    }

    public void addNewUser(User user){
        User userSearch =
                userRepository.findUserBySession(user.getSession());

        if(userSearch != null){
            throw new IllegalStateException("User already exists");
        }
        userRepository.save(user);
    }


    public void deleteUser(Long id) {
        if(!userRepository.existsById(id)){
            throw new IllegalStateException("User with ID: " + id + "Does not exist");
        }
        userRepository.deleteById(id);

    }
}
