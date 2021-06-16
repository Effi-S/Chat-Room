package com.web_course.chat_app.api.message;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageService {

    private final MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public List<Message> getMessages(){
       return messageRepository.findAll();
    }

    public void addMessage(Message message) {
        messageRepository.save(message);
    }

    public List<Message> findAllMessagesWithSubString(String subString){
        return messageRepository.findAllMessagesWithSubString(subString);
    }

    public List<Message> findAllMessagesFromUser(String username) {
        return messageRepository.findMessagesFromUser(username);
    }
}
