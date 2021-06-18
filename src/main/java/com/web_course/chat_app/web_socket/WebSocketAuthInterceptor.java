package com.web_course.chat_app.web_socket;

import com.web_course.chat_app.api.user.User;
import com.web_course.chat_app.api.user.UserService;
import com.web_course.chat_app.exceptions.UserNotRegisteredException;
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
    private final WebSocketAuthenticatorService authService;
    private final UserService userService;

    private static final String USER = "username";
    private static final String PASS = "password";


    @Autowired
    public WebSocketAuthInterceptor(WebSocketAuthenticatorService service, UserService userService){

        this.authService = service;
        this.userService = userService;
    }
    @Override
    public org.springframework.messaging.Message<?> preSend(Message<?> message, MessageChannel channel) {

//         Instantiate an object for retrieving the STOMP headers
        final StompHeaderAccessor accessor = MessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
        assert accessor != null;
        if(accessor.getCommand() == StompCommand.CONNECT){
            String sess = accessor.getSessionId();
            System.out.println("Sess: " + sess);

            final String username = accessor.getFirstNativeHeader(USER);
            final String password = accessor.getFirstNativeHeader(PASS);

            Optional<User> userSearch = userService.getUserBySession(sess);
            if(userSearch.isEmpty()){
                System.out.println("userSearch is empty!");
//                throw new UserNotRegisteredException();
            } else if (!userSearch.get().isActive()){
//                redirectToLogin(serverHttpResponse);
            }

            // authenticate the user and if that's successful add their user information to the headers.
//            UsernamePasswordAuthenticationToken user = service.getAuthenticatedOrFail(username, password);
//            accessor.setUser(user);

        }

        return message;

    }
}
