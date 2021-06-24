package com.web_course.chat_app.api.message;

import javax.persistence.*;
import java.time.Instant;

/**
 * Message holds all information about a message.<br/>
 * Messages are stored in "message" table in DB.
 */
@Entity
@Table
public class Message {
    /** Id of the DB element. <br />
     *  Generated automatically by Spring in sequential order**/
    @Id
    @SequenceGenerator(
            name = "message_sequence",
            sequenceName = "message_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "message_sequence"
    )
    private Long mid;
    /** The Content of the message **/
    private String message;
    /** The username of the sender of the message */
    private String username;
    /** Type of message, see MessageType Enum.*/
    private MessageType msgType;


    /**
     * Instantiates a new Message.
     *
     * @param message  the message
     * @param username the username
     */
    public Message(String message, String username) {
        this.message = message;
        this.username = username;
        this.mid = Instant.now().toEpochMilli();
        this.msgType = MessageType.REGULAR;
    }

    /**
     * Instantiates a new Message.
     *
     * @param message  the message
     * @param username the username
     * @param msgType  the msg type
     */
    public Message(String message, String username, MessageType msgType) {
        this.message = message;
        this.username = username;
        this.msgType = msgType;
        this.mid = Instant.now().toEpochMilli();
    }

    /**
     * Instantiates a new Message.<br />
     * Blank constructor for Hibernate. <br />
     * This constructor should not be used otherwise.
     */
    public Message() {

    }

    /**
     * Gets message content.
     *
     * @return The message to get
     */
    public String getMessage() { return message; }

    /**
     * Gets username of who sent the message.
     *
     * @return The username to get.
     */
    public String getUsername() { return username; }

    /**
     * Gets mid.
     *
     * @return the mid to get.
     */
    public Long getMid() { return mid; }

    /**
     * Gets type of message, see MessageType Enum.
     *
     * @return The type of the message.
     */
    public MessageType getType() { return msgType; }


    /**
     * Sets username.
     *
     * @param username the username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Sets message content.
     *
     * @param message The message content to set.
     */
    public void setMessage(String message) {
        this.message = message;
    }

    /**
     * Sets type of message, see MessageType Enum.
     *
     * @param msgType the msg type to set.
     */
    public void setType(MessageType msgType) { this.msgType = msgType; }
}
