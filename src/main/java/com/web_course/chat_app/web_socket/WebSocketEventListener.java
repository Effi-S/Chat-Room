package com.web_course.chat_app.web_socket;

import com.web_course.chat_app.api.message.Message;
import com.web_course.chat_app.api.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.stomp.StompHeaderAccessor;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.messaging.AbstractSubProtocolEvent;
import org.springframework.web.socket.messaging.SessionConnectedEvent;
import org.springframework.web.socket.messaging.SessionDisconnectEvent;

import java.util.Objects;

@Component
public class WebSocketEventListener {


    private final SimpMessageSendingOperations  sendingOperations;
    private final UserService userService;
    SimpMessageHeaderAccessor  headerAccessor;

    @Autowired
    WebSocketEventListener(SimpMessageSendingOperations sendingOperations, UserService userService){

        this.sendingOperations = sendingOperations;
        this.userService = userService;
    }

    @EventListener
    public void handleConnect(final SessionConnectedEvent event)  {
        System.out.println("New Connection..");
        String username = getUsernameFromEvent(event);
         final Message message =  new Message(username + " Connected.",
                "chat-app");
        sendingOperations.convertAndSend("topic/messages", message);
    }

    private String getUsernameFromEvent(AbstractSubProtocolEvent event) {
        final StompHeaderAccessor headerAccessor = StompHeaderAccessor.wrap(event.getMessage());
        return "username";
//        return (String) Objects.requireNonNull(headerAccessor.getSessionAttributes()).get("username");

    }

    @EventListener
    public void handleDisconnect(SessionDisconnectEvent event){
        String username = getUsernameFromEvent(event);
        final Message message =  new Message(username + " Disconnected.",
                                            "chat-app");
        System.out.println(".. Disconnect event .. ");
        sendingOperations.convertAndSend("topic/messages", message);
    }

}
