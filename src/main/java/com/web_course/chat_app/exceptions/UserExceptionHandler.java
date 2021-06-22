package com.web_course.chat_app.exceptions;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;



@ControllerAdvice
public class UserExceptionHandler{

    @ExceptionHandler(value={UserAlreadyRegisteredException.class, UserNotExistException.class})
        public ModelAndView backToLoginPage(Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("msg", ex.getMessage());
        mav.setViewName("/login");
        return mav;
        }
}
