package com.web_course.chat_app.web_socket;
import com.web_course.chat_app.api.message.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Objects;


@Controller
public class ChatController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message sendMessage(@Payload final Message message){
        return message;
    }

    @MessageMapping("/new-user")
    @SendTo("/topic/messages")
    public Message  userLogin(@Payload final Message message,
                            SimpMessageHeaderAccessor accessor){
        String username = message.getUsername();
        Objects.requireNonNull(accessor.getSessionAttributes()).put("username", username);
        return new Message("Joined the chat", username);
  }
}
