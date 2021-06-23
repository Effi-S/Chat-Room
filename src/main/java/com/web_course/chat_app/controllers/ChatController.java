package com.web_course.chat_app.controllers;
import com.web_course.chat_app.api.message.Message;
import com.web_course.chat_app.api.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import java.util.Objects;


/**
 * This Class is in charge of mapping messages sent by WebSocket.
 */
@Controller
public class ChatController {

    /**
     * The Message service. <br/>
     * So we can add / remove messages as we wish.
     */
    MessageService messageService;

    /**
     * Instantiates a new Chat controller.
     *
     * @param messageService the message service Bean.
     */
    @Autowired
    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * Re-routes from "app/chat" to "app/topic/messages" for consumption.
     *
     * @param message The message sent to "app/chat".
     * @return The message, re-routed to "app/topic/message".
     */
    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message sendMessage(@Payload final Message message){
        messageService.addMessage(message);
        return message;
    }

    /**
     * User login message.
     * Re-routes from "app/new-user" to "app/topic/messages" for consumption. <br/>
     * Indicates a new user logged on. <br/>
     * The username of the user logged on is retrieved from the Session attributes.
     *
     * @param message  The message sent to "app/chat".
     * @param accessor The accessor is used  for getting the username from the session attributes.
     * @return The message after adding the username, re-routed to "app/topic/message".
     */
    @MessageMapping("/new-user")
    @SendTo("/topic/messages")
    public Message  userLogin(@Payload final Message message,
                            SimpMessageHeaderAccessor accessor){

        String username = message.getUsername();
        Objects.requireNonNull(accessor.getSessionAttributes()).put("username", username);
        return new Message("Joined the chat", username);
  }
}
