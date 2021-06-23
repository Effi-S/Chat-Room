package com.web_course.chat_app.api.message;

/**
 * The enum Message type.<br />
 * This determines the type of message that was sent.<br />
 * Based on a messages type, the front end can decide how to react.
 *
 */
public enum MessageType {
    /**
     * Regular message type. <br />
     * The default message type. <br />
     * Simply a message being sent between users.
     */
    REGULAR,
    /**
     * Connect message type.<br />
     * A message of this type indicates that a user has entered the chat.
     */
    CONNECT,
    /**
     * Disconnect message type.<br />
     * A message of this type indicates that a user has left the chat.
     */
    DISCONNECT
}
