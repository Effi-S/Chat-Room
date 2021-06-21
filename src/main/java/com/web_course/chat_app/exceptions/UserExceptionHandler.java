package com.web_course.chat_app.exceptions;


import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class UserExceptionHandler{

    @ExceptionHandler(value={UserAlreadyRegisteredException.class, UserNotExistException.class})
        public ModelAndView backToLoginPage(Exception ex, HttpServletRequest req) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("msg", ex.getMessage());
        mav.setViewName("/login");
        return mav;
        }
}
