package com.web_course.chat_app.web_socket;

import com.web_course.chat_app.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Objects;

@Component
public class WebSocketEventListener {


    private SimpMessageSendingOperations  sendingOperations;

    @Autowired
    WebSocketEventListener(SimpMessageSendingOperations  sendingOperations){
        this.sendingOperations = sendingOperations;
    }

    @EventListener
    public void handleConnect(final SessionConnectedEvent event){
        System.out.println("New Connection");
    }

    @EventListener
    public void handleDisconnect(SessionDisconnectEvent event){
        final StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        final String username = (String) Objects.requireNonNull(headerAccessor.getSessionAttributes()).get("username");

        final Message massage = new Message();
    }
}
