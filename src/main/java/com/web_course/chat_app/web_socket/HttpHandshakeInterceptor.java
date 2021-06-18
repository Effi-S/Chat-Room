package com.web_course.chat_app.web_socket;

import com.web_course.chat_app.api.user.User;
import com.web_course.chat_app.api.user.UserService;
import com.web_course.chat_app.exceptions.UserInactiveException;
import com.web_course.chat_app.exceptions.UserNotRegisteredException;
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
import java.util.Optional;

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

        String sessionId = getSessionID(serverHttpRequest);
        if (sessionId != null){
            attributes.put("sessionId", sessionId);
            System.out.println("SessionId: " + sessionId);
        }
        Optional<User> userSearch = userService.getUserBySession(sessionId);
        if(userSearch.isEmpty()){
            System.out.println("userSearch is empty!");
//            throw new UserNotRegisteredException();
        } else if (!userSearch.get().isActive()){
            System.out.println("user inactive!");
            throw new UserInactiveException();
        } else {
            userSearch.get().setSession(sessionId);
            userSearch.get().setActive(true);
        }
            return true;
    }

    @Override
    public void afterHandshake(ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse, WebSocketHandler webSocketHandler, Exception e) {
    }

    private String getSessionID(ServerHttpRequest serverHttpRequest) {
        if (serverHttpRequest instanceof ServletServerHttpRequest servletRequest) {
            HttpSession session = servletRequest.getServletRequest().getSession();
            return session.getId();
            }
        return null;
    }
}
