package com.web_course.chat_app.web_socket;
import com.web_course.chat_app.api.message.Message;
import com.web_course.chat_app.api.message.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Objects;


@Controller
public class ChatController {

    MessageService messageService;

    @Autowired
    public ChatController(MessageService messageService) {
        this.messageService = messageService;
    }

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message sendMessage(@Payload final Message message){
        messageService.addMessage(message);
        return message;
    }

    @MessageMapping("/new-user")
    @SendTo("/topic/messages")
    public Message  userLogin(@Payload final Message message,
                            SimpMessageHeaderAccessor accessor){

        String username = message.getUsername();
        System.out.println("new-user: " + username);
        Objects.requireNonNull(accessor.getSessionAttributes()).put("username", username);
        return new Message("Joined the chat", username);
  }
}
