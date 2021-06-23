package com.web_course.chat_app.exceptions;

/**
 * This is a RuntimeException indicating a User already registered is trying to register again.
 */
public class UserAlreadyRegisteredException extends RuntimeException {

    /**
     * Instantiates a new User already registered exception.
     */
    public UserAlreadyRegisteredException() {
    }

    /**
     * Instantiates a new User already registered exception.
     *
     * @param message the message
     */
    public UserAlreadyRegisteredException(String message) {
        super(message);
    }

    /**
     * Instantiates a new User already registered exception.
     *
     * @param message the message
     * @param cause   the cause
     */
    public UserAlreadyRegisteredException(String message, Throwable cause) {
        super(message, cause);
    }

    /**
     * Instantiates a new User already registered exception.
     *
     * @param cause the cause
     */
    public UserAlreadyRegisteredException(Throwable cause) {
        super(cause);
    }

    /**
     * Instantiates a new User already registered exception.
     *
     * @param message            the message
     * @param cause              the cause
     * @param enableSuppression  the enable suppression
     * @param writableStackTrace the writable stack trace
     */
    public UserAlreadyRegisteredException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
