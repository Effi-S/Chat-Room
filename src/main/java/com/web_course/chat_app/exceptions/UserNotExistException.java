package com.web_course.chat_app.exceptions;

/**
 * This is a RuntimeException indicating no User is registered but still an access attempt was made to the chatroom.
 */
public class UserNotExistException extends RuntimeException {


    /**
     * Instantiates a new User not exist exception.
     *
     * @param message the message
     */
    public UserNotExistException(String message) {
        super(message);
    }

    /**
     * Instantiates a new User not exist exception.
     */
    public UserNotExistException() {
    }

    /**
     * Instantiates a new User not exist exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public UserNotExistException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new User not exist exception.
     *
     * @param cause the cause
     */
    public UserNotExistException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new User not exist exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public UserNotExistException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
