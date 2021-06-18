package com.web_course.chat_app.exceptions;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {

        @ExceptionHandler(value={UserNotRegisteredException.class, UserInactiveException.class})
        public String notLoggedIn() {
            return "forward:/login";
        }
}
