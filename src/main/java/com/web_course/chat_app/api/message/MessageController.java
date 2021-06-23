package com.web_course.chat_app.api.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * The type Message controller. <br/>
 * This acts as the endpoint to our Messages API.
 */
@RestController
@RequestMapping(path="api/v1/messages")
public class MessageController {

    private final MessageService messageService;

    /**
     * Instantiates a new Message controller.
     *
     * @param messageService The message service Bean.
     */
    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * Get list of all messages in DB.
     *
     * @return the list
     */
    @GetMapping("/get")
    public List<Message> getMessages(){
        return messageService.getMessages();
    }

    /**
     * Add a message to the DB.
     *
     * @param message the message
     */
    @PostMapping("/post")
    public void addMessage(@RequestBody Message message){
       messageService.addMessage(message);
    }

    /**
     * Find messages by sub string list. <br/>
     * Given a "sub-string", returns all messages that contain this sub-string.
     *
     * @param subString the sub string
     * @return the list
     */
    @GetMapping("/find/sub_string/{sub}")
    public List<Message> findBySubString(@PathVariable("sub") String subString){
        return messageService.findAllMessagesWithSubString(subString);
    }

    /**
     * Find user messages list. <br/>
     * Given a useranme returns all of the Messages sent by this user.
     *
     * @param username the username
     * @return the list
     */
    @GetMapping("/find/user/{username}")
    public List<Message> findUserMessages(@PathVariable("username") String username){
        return messageService.findAllMessagesFromUser(username);
    }

    /**
     * Provides the user with the last 5 messages.
     *
     * @return the list
     */
    @GetMapping("/find/last5")
    public List<Message> findUserMessages(){
        return messageService.getLast5Messages();
    }
}
