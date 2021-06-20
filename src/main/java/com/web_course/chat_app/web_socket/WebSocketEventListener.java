package com.web_course.chat_app.web_socket;

import com.web_course.chat_app.api.message.Message;
import com.web_course.chat_app.api.message.MessageType;
import com.web_course.chat_app.api.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Objects;

@Component
public class WebSocketEventListener {


    private final SimpMessageSendingOperations  sendingOperations;
    private final UserService userService;

    @Autowired
    WebSocketEventListener(SimpMessageSendingOperations sendingOperations, UserService userService){

        this.sendingOperations = sendingOperations;
        this.userService = userService;
    }
    @EventListener
    public void handleConnect(SessionConnectEvent event){
        final StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        final String username = (String) Objects.requireNonNull(accessor.getSessionAttributes()).get("username");
        sendingOperations.convertAndSend("/topic/messages",
                new Message("Connected", username, MessageType.CONNECT));
    }

    @EventListener
    public void handleDisconnect(SessionDisconnectEvent event){
        final StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        final String username = (String) Objects.requireNonNull(accessor.getSessionAttributes()).get("username");
        userService.deleteUser(username);
        System.out.println("DISCONNECT EVENT " + username);
        sendingOperations.convertAndSend("/topic/messages",
                new Message("Disconnected", username, MessageType.DISCONNECT));
        sendingOperations.convertAndSend("/topic/messages",
                new Message("Left the Chat.", username, MessageType.REGULAR));

    }
}
