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

@Component
@Controller
public class HttpHandshakeInterceptor implements HandshakeInterceptor {

    private final UserService userService;

    @Autowired
    public HttpHandshakeInterceptor(UserService userService) {
        this.userService = userService;
    }

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

    private String getUsernameFromSession(ServerHttpRequest serverHttpRequest) {
        if (serverHttpRequest instanceof ServletServerHttpRequest servletRequest) {
            HttpSession session = servletRequest.getServletRequest().getSession();
            return (String) session.getAttribute("username");
            }
        return null;
    }
}
