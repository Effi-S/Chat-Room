package com.web_course.chat_app.api.user;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * In this class we can populate our User DB. <br />
 * At the moment the only User populated is our chat-app user.
 */
@Configuration
public class UserConfig {

    /**
     * User command line runner command line runner.
     *
     * @param repository the repository
     * @return the command line runner
     */
    @Bean
    CommandLineRunner userCommandLineRunner(UserRepository repository){
        return args -> repository.save(new User("chat-app",
                "chat-app-SESSION-Id-Dummy")
        );
    }
}
