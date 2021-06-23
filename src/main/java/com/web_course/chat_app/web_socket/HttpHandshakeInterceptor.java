package com.web_course.chat_app.web_socket;

import com.web_course.chat_app.api.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;
import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * The type Http handshake interceptor.
 */
@Component
@Controller
public class HttpHandshakeInterceptor implements HandshakeInterceptor {

    /**The user Service Bean*/
    private final UserService userService;

    /**
     * Instantiates a new Http handshake interceptor.
     *
     * @param userService the user service
     */
    @Autowired
    public HttpHandshakeInterceptor(UserService userService) {
        this.userService = userService;
    }

    /**
     * This method intercepts the http handshake. <br/>
     * Adds the username to Stomp Attributes from Http Session attributes.
     *
     * @param serverHttpRequest used for retrieving the username from session.
     * @param serverHttpResponse see HandshakeInterceptor
     * @param webSocketHandler see HandshakeInterceptor
     * @param attributes - username attribute is added here.
     * @return see HandshakeInterceptor
     */
    @Override
    public boolean beforeHandshake(org.springframework.http.server.ServerHttpRequest serverHttpRequest,
                                   org.springframework.http.server.ServerHttpResponse serverHttpResponse,
                                   WebSocketHandler webSocketHandler, Map<String, Object> attributes) {

        String username = getUsernameFromSession(serverHttpRequest);
        if (username != null){
            attributes.put("username", username);
        }
            return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
    }
    /**
     * Given a HttpRequest returns the username from the request session attributes.
     * @param serverHttpRequest - The Request to derive username from.
     * @return null if the serverHttpRequest Object is not of type ServletServerHttpRequest.<br/>
     *         Otherwise, returns the username attribute from the ServletServerHttpRequest session
     */
    private String getUsernameFromSession(ServerHttpRequest serverHttpRequest) {
        if (serverHttpRequest instanceof ServletServerHttpRequest servletRequest) {
            HttpSession session = servletRequest.getServletRequest().getSession();
            return (String) session.getAttribute("username");
            }
        return null;
    }
}
