package com.web_course.chat_app.user;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class UserConfig {

    @Bean
    CommandLineRunner commandRunner(UserRepository repository ){
        return args -> {
            User chatapp = new User("chat-app", "1234");
            User john = new User("john", "4567");
            repository.saveAll(List.of(chatapp, john));

        };
    }
}
