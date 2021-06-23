package com.web_course.chat_app.web_socket;

import com.web_course.chat_app.api.user.User;
import com.web_course.chat_app.api.user.UserService;
import com.web_course.chat_app.exceptions.UserNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Optional;

/**
 * The type Web socket auth interceptor. <br/>
 * This Class is Disabled.<br/>
 * Can be used to validate the messages being passed.
 */
@Service
public class WebSocketAuthInterceptor implements ChannelInterceptor {
    private final UserService userService;

    /**
     * Instantiates a new Web socket auth interceptor.
     *
     * @param userService the user service
     */
    @Autowired
    public WebSocketAuthInterceptor(UserService userService){
        this.userService = userService;
    }
    @Override
    public org.springframework.messaging.Message<?> preSend(Message<?> message, MessageChannel channel) {
//        System.out.println("WebSocketAuthInterceptor");
//        Instantiate an object for retrieving STOMP headers
//        final StompHeaderAccessor accessor = SimpMessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
//        assert accessor != null;
//        if (accessor.getCommand() == StompCommand.CONNECT) {
//
//            final String username = (String) accessor.getSessionAttributes().toString();
//            System.out.println("Session Atters: " + username);
//            Optional<User> userSearch = userService.getUser(username);
//            if (userSearch.isEmpty()) {
//                System.out.println("User: " + username + " didn't log in!");
////                throw new UserNotExistException("User" + username + "didn't log in!");
//            }
//        }
        return message;
    }
}
