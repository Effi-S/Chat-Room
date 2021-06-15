package com.web_course.chat_app.message;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class MessageConfig {
    @Bean
    CommandLineRunner commandLineRunner(MessageRepository repository){
        return args -> repository.saveAll(List.of(new Message(
                                    "Welcome to the chat!",
                                    "chat-app")
                                ));
    }
}
