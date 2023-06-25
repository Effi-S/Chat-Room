package com.web_course.chat_app.web_socket;

import com.web_course.chat_app.api.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.config.ChannelRegistration;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;

/**
 * This class configures our STOMP endpoints.<br/>
 * Connects to Event listeners and Handshake interceptors.
 */
@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    /** User service layer connection for Querying about users */
    private final UserService userService;

    /** channel interceptor for authenticating messaging */
    private final WebSocketAuthInterceptor channelInterceptor;

    /**
     * Instantiates a new Web socket config.
     *
     * @param userService        The user service.
     * @param channelInterceptor The channel interceptor.
     */
    @Autowired
    public WebSocketConfig(UserService userService, WebSocketAuthInterceptor channelInterceptor) {
        this.userService = userService;
        this.channelInterceptor = channelInterceptor;
    }

    /**
     * Here initialize STOMP and add Interceptors.<br/>
     * As well, we add a SockJS fallback option in-case STOMP is not supported by our browser.
     * @param registry - The registry object used for all the registrations described above.
     */
    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/stomp")
                .addInterceptors(new HttpHandshakeInterceptor(userService))
                .withSockJS(); // allow SockJS fallback option.
        WebSocketMessageBrokerConfigurer.super.registerStompEndpoints(registry);
    }

    /**
     * Here we add our stomp endpoint.
     * @param registry The registry object used for adding endpoints.
     */
    @Override
    public void configureMessageBroker(final MessageBrokerRegistry registry){
        registry.setApplicationDestinationPrefixes("/app");
        registry.enableSimpleBroker("/topic");
    }

    /** Here we register a Channel Interceptor **/
    @Override
    public void configureClientInboundChannel(ChannelRegistration registration) {
        registration.interceptors(channelInterceptor);
    }
    @Bean
    WebMvcConfigurer corsConfig() {
        return new WebMvcConfigurer() {

            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/ws/**")
                        .allowedMethods("GET", "POST", "PUT", "DELETE")
                        .allowedHeaders("*")
                        .allowedOrigins("*");
            }
        };
    }
}
