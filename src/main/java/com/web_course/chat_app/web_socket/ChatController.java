package com.web_course.chat_app.web_socket;

import com.web_course.chat_app.message.Message;
import com.web_course.chat_app.user.User;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.stereotype.Controller;

import java.util.Objects;

@Controller
public class ChatController {
    @MessageMapping("/chat.send")
    @SendTo("topic/public")
    public Message sendMessage(@Payload final Message message){
        return message;
    }
    @MessageMapping("/chat.userEnter")
    @SendTo
    public Message userEnter(@Payload final User user,
                           SimpMessageHeaderAccessor headerAccessor ){
        Objects.requireNonNull(headerAccessor.getSessionAttributes()).put("username", user.getName());
        return new Message(123L,
                user.getName() + " connected.",
                1L,
                "chat-app");
    }
}
