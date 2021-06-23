package com.web_course.chat_app.exceptions;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;


/**
 * This ControllerAdvice is in charge of routing all User related exceptions.
 */
@ControllerAdvice
public class UserExceptionHandler{

    /**
     * Routes Back to login page .
     *
     * @param ex the ex.
     * @return the model and view
     */
    @ExceptionHandler(value={UserAlreadyRegisteredException.class, UserNotExistException.class})
        public ModelAndView backToLoginPage(Exception ex) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("msg", ex.getMessage());
        mav.setViewName("/login");
        return mav;
        }
}
