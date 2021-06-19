package com.web_course.chat_app.exceptions;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class UserExceptionHandler{

    @ExceptionHandler(value={UserAlreadyRegisteredException.class, UserNotExistException.class})
        public String backToLoginPage() {
        System.out.println("Error.. redirecting to login!");
            return "forward:/login";
        }
}
