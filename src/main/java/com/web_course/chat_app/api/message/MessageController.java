package com.web_course.chat_app.api.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="api/v1/messages")
public class MessageController {

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/get")
    public List<Message> getMessages(){
        return messageService.getMessages();
    }

    @PostMapping("/post")
    public void addMessage(@RequestBody Message message){
       messageService.addMessage(message);
    }

    @GetMapping("/find/sub_string/{sub}")
    public List<Message> findBySubString(@PathVariable("sub") String subString){
        return messageService.findAllMessagesWithSubString(subString);
    }

    @GetMapping("/find/user/{username}")
    public List<Message> findUserMessages(@PathVariable("username") String username){
        return messageService.findAllMessagesFromUser(username);
    }

}
