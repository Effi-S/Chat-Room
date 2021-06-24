package com.web_course.chat_app.web_socket;
import org.springframework.messaging.support.ChannelInterceptor;
import org.springframework.stereotype.Service;

//import java.util.Map;
//import java.util.Objects;
//import java.util.Optional;
//import com.web_course.chat_app.api.user.User;
//import org.springframework.messaging.support.MessageHeaderAccessor;
//import com.web_course.chat_app.api.user.UserService;
//import com.web_course.chat_app.exceptions.UserNotExistException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.messaging.Message;
//import org.springframework.messaging.MessageChannel;
//import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
//import org.springframework.messaging.simp.stomp.StompCommand;
//import org.springframework.messaging.simp.stomp.StompHeaderAccessor;

/**
 * Web socket authentication interceptor. <br/>
 * This interceptor Class *can be* used to validate the messages being passed.<br/>
 * Unfortunately, no authentication was implemented in this project so this has no use to us.<br/>
 * <br />
 *
 * To implement this class uncomment the commented-out code.
 */
@Service
public class WebSocketAuthInterceptor implements ChannelInterceptor {

//    @Override
//    public org.springframework.messaging.Message<?> preSend(Message<?> message, MessageChannel channel) {
//
//////        Instantiate an object for retrieving STOMP headers
////        final StompHeaderAccessor accessor = SimpMessageHeaderAccessor.getAccessor(message, StompHeaderAccessor.class);
////        assert accessor != null;
//////        *** Uncomment to get username ***
////        final String username = (String) Objects.requireNonNull(accessor.getSessionAttributes()).get("username");
//
//////      ***  Uncomment to customize interception for each type***
////        StompCommand command = accessor.getCommand();
////        if(command == StompCommand.CONNECT) {
////
////            // Implement CONNECT interception here
////
////        } else if (command== StompCommand.DISCONNECT) {
////
////            // Implement DISCONNECT interception here
////
////        } else if(command == StompCommand.MESSAGE) {
////
////            // Implement  MESSAGE interception here
////
////        }
//
//        return message;
//    }
}
