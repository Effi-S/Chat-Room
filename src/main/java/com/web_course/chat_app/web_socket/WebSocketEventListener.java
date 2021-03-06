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

/**
 * This class Handles Websocket Events. <br/>
 * Custom Handles for CONNECT, DISCONNECT exist here.
 */
@Component
public class WebSocketEventListener {

    /** For redirecting messages from input endpoint to output endpoint */
    private final SimpMessageSendingOperations  sendingOperations;
    /** UserService Layer for Querying about Users */
    private final UserService userService;

    /**
     * Instantiates a new Web socket event listener.
     *
     * @param sendingOperations the sending operations
     * @param userService       the user service
     */
    @Autowired
    WebSocketEventListener(SimpMessageSendingOperations sendingOperations, UserService userService){

        this.sendingOperations = sendingOperations;
        this.userService = userService;
    }

    /**
     * Handle connect. <br/>
     * Sends a " some-user : Connected" message.
     *
     * @param event the event
     */
    @EventListener
    public void handleConnect(SessionConnectEvent event){

        final StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        final String username = (String) Objects.requireNonNull(accessor.getSessionAttributes()).get("username");

//        if(userService.getUserByUsername(username).isEmpty()) // this can happen if page refreshed.
//            userService.addNewUser(username);
        System.out.println("CONNECT with " + username);
        sendingOperations.convertAndSend("/topic/messages",
                new Message("Joined the chat", username, MessageType.CONNECT));
    }

    /**
     *  Handle disconnect.
     *  Sends a " some-user : Disconnected" message.
     * @param event the event
     */
    @EventListener
    public void handleDisconnect(SessionDisconnectEvent event){
        final StompHeaderAccessor accessor = StompHeaderAccessor.wrap(event.getMessage());
        final String username = (String) Objects.requireNonNull(accessor.getSessionAttributes()).get("username");

        if(userService.getUserByUsername(username).isPresent())
            userService.deleteUser(username);

        sendingOperations.convertAndSend("/topic/messages",
                new Message("Left the Chat.", username, MessageType.DISCONNECT));

    }

}
