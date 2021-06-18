package com.web_course.chat_app.web_socket;

import com.web_course.chat_app.api.user.User;
import com.web_course.chat_app.api.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.WebSocketHandler;
import org.springframework.web.socket.server.HandshakeInterceptor;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Map;
import java.util.Optional;

@Component
public class HttpHandshakeInterceptor implements HandshakeInterceptor {

    private final UserService userService;

    @Autowired
    public HttpHandshakeInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean beforeHandshake(org.springframework.http.server.ServerHttpRequest serverHttpRequest,
                                   org.springframework.http.server.ServerHttpResponse serverHttpResponse,
                                   WebSocketHandler webSocketHandler, Map<String, Object> attributes)  {

        String sessionId = getSessionID(serverHttpRequest);
        if (sessionId != null){
            attributes.put("sessionId", sessionId);
            System.out.println("SessionId: " + sessionId);

            Optional<User> userSearch = userService.getUserBySession(sessionId);

//            if(userSearch.isEmpty()){
//                redirectToLogin(serverHttpResponse);
//             } else if (!userSearch.get().isActive()){
//                redirectToLogin(serverHttpResponse);
//            }
        }
        return true;
    }
    private void redirectToLogin(ServerHttpResponse serverHttpResponse) throws IOException {
        if (serverHttpResponse instanceof ServletServerHttpResponse servletServerHttpResponse) {
            System.out.println("Redirecting to Login!");
            servletServerHttpResponse.getServletResponse().sendRedirect("/login");
        }
    }

    private String getSessionID(ServerHttpRequest serverHttpRequest) {
        if (serverHttpRequest instanceof ServletServerHttpRequest servletRequest) {
            HttpSession session = servletRequest.getServletRequest().getSession();
            return session.getId();
            }
        return null;
    }

    @Override
    public void afterHandshake(org.springframework.http.server.ServerHttpRequest serverHttpRequest,
                               org.springframework.http.server.ServerHttpResponse serverHttpResponse,
                               WebSocketHandler webSocketHandler, Exception e) {
        String sessionId = getSessionID(serverHttpRequest);
        Optional<User> userSearch = userService.getUserBySession(sessionId);
        assert userSearch.isPresent(): "Could not get user (Unexpected). ";

        userSearch.get().setActive(true);
        userSearch.get().setSession(sessionId);

//        if (serverHttpRequest instanceof ServletServerHttpRequest servletRequest) {
//            HttpSession session = servletRequest.getServletRequest().getSession();
//            String sessionId = session.getId();
//            System.out.println("SessionId: " + sessionId);
//        }

    }
}
