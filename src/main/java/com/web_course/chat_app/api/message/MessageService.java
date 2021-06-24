package com.web_course.chat_app.api.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * The type Message service.<br />
 * This is the Service Layer of  "message" DB.
 */
@Service
public class MessageService {

    /** Repository Layer **/
    private final MessageRepository messageRepository;

    /**
     * Instantiates a new Message service.
     *
     * @param messageRepository the message repository
     */
    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    /**
     * Get list of all messages .
     *
     * @return the list of all messages in DB.
     */
    public List<Message> getMessages(){
       return messageRepository.findAll();
    }

    /**
     * Add a message.
     *
     * @param message the message to add.
     */
    public void addMessage(Message message) {
        messageRepository.save(message);
    }

    /**
     * Find all messages with sub string list.
     *
     * @param subString the sub-string to search for.
     * @return list of messages
     */
    public List<Message> findAllMessagesWithSubString(String subString){
        return messageRepository.findAllMessagesWithSubString(subString);
    }

    /**
     * Finds all messages from a given username.
     *
     * @param username the username to search for.
     * @return the list of messages found.
     */
    public List<Message> findAllMessagesFromUser(String username) {
        return messageRepository.findMessagesFromUser(username);
    }

    /**
     * Gets last 5 messages from db.
     *
     * @return the list of messages found
     */
    public List<Message> getLast5Messages(){
        List<Message> last5 = messageRepository.findLastI(PageRequest.ofSize(5));
        Collections.reverse(last5);
        return last5;
    }
}
