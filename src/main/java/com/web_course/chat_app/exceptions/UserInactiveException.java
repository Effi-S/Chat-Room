package com.web_course.chat_app.exceptions;

public class UserInactiveException extends RuntimeException {
    public UserInactiveException() {
    }

    public UserInactiveException(String message) {
        super(message);
    }

    public UserInactiveException(String message, Throwable cause) {
        super(message, cause);
    }

    public UserInactiveException(Throwable cause) {
        super(cause);
    }

    public UserInactiveException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
