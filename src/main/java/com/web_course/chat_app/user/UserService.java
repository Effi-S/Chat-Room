package com.web_course.chat_app.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User getUser(String id) {
        User userSearch = userRepository.findUserById(id);
        if(userSearch == null){
            throw new IllegalStateException("User Does not exists");
        }
        return userSearch;
    }

    public void addNewUser(User user){
        User userSearch =
                userRepository.findUserById(user.getId());

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
