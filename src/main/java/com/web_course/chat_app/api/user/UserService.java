package com.web_course.chat_app.api.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public Optional<User> getUserBySession(String session) {
        Optional<User> user = userRepository.findUserBySession(session);
//        if(user.isEmpty()){
//            new User("Johnny", "Walker");
////            throw new IllegalStateException("User Does not exists");
//        }
        return user;
    }

    public void addNewUser(User user){
        Optional<User> userSearch =
                userRepository.findUserBySession(user.getSession());

        if(userSearch.isPresent()){
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
