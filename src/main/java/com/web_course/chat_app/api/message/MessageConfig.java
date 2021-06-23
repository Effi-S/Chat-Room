package com.web_course.chat_app.api.message;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * In this class we can instantiate our Massage DB. <br />
 * At the moment the only Message instantiated is a Welcome message from chat-app user.
 */
@Configuration
public class MessageConfig {
    /**
     * Command line runner. <br/>
     * Here we can add Messages to out message Repository.
     *
     *
     * @param repository the repository
     * @return the command line runner
     */
    @Bean
    CommandLineRunner commandLineRunner(MessageRepository repository){
        return args -> repository.saveAll(List.of(
                new Message("Welcome to the chat!",
                        "chat-app")
        ));
    }
}
