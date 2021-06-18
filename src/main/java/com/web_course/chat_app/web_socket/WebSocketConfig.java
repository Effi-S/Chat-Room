package com.web_course.chat_app.web_socket;

import com.web_course.chat_app.api.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    private final UserService userService;

    @Autowired
    public WebSocketConfig(UserService userService) {
        this.userService = userService;
    }

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomp")
                .addInterceptors(new HttpHandshakeInterceptor(userService))
                .withSockJS(); // allow SockJS fallback option.
        WebSocketMessageBrokerConfigurer.super.registerStompEndpoints(registry);
    }

    @Override
    public void configureMessageBroker(final MessageBrokerRegistry registry){
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }

}
