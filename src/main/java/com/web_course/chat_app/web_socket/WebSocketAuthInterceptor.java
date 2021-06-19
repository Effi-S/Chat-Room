package com.web_course.chat_app.web_socket;

import com.web_course.chat_app.api.user.User;
import com.web_course.chat_app.api.user.UserService;
import com.web_course.chat_app.exceptions.UserNotExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.simp.stomp.StompCommand;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.messaging.support.MessageHeaderAccessor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class WebSocketAuthInterceptor implements ChannelInterceptor {
    private final UserService userService;

    private static final String USER = "username";

    @Autowired
    public WebSocketAuthInterceptor(UserService userService){
        this.userService = userService;
    }
    @Override
    public org.springframework.messaging.Message<?> preSend(Message<?> message, MessageChannel channel) {

//         Instantiate an object for retrieving STOMP headers
        final StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        assert accessor != null;
        if (accessor.getCommand() == StompCommand.CONNECT) {

            final String username = accessor.getFirstNativeHeader(USER);

            Optional<User> userSearch = userService.getUser(username);
            if (userSearch.isEmpty()) {
                System.out.println("User" + username + "didn't log in!");
//                throw new UserNotExistException("User" + username + "didn't log in!");
            }
        }
        return message;
    }
}
