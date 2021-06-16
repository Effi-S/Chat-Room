package com.web_course.chat_app.web_socket;
import com.web_course.chat_app.message.Message;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


@Controller
public class ChatController {

    @MessageMapping("/chat")
    @SendTo("/topic/messages")
    public Message sendMessage(@Payload final Message message){
        System.out.println("Redirecting message: " + message.getMessage()
                + " " +  message.getUsername());
        return message;
    }
}
