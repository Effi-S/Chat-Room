package com.web_course.chat_app.web_socket;

import com.web_course.chat_app.api.message.Message;
import com.web_course.chat_app.api.user.User;
import com.web_course.chat_app.api.user.UserService;
import com.web_course.chat_app.exceptions.UserAlreadyRegisteredException;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.AbstractSubProtocolEvent;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Map;
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
    public void handleDisconnect(SessionDisconnectEvent event){
        final StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        final String username = (String) Objects.requireNonNull(accessor.getSessionAttributes()).get("username");
        userService.deleteUser(username);
        sendingOperations.convertAndSend("/topic/messages", new Message("Disconnected", username));
    }
}
