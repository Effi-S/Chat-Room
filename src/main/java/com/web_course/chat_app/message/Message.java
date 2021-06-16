package com.web_course.chat_app.message;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table
public class Message {
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
    private String message;
    private String username;


    public Message(String message, String username) {

        this.message = message;
        this.username = username;
        this.mid = Instant.now().toEpochMilli();
    }

    public Message() {

    }

    public String getMessage() {
        return message;
    }
    public String getUsername() {
        return username;
    }
    public Long getMid() {
        return mid;
    }


    public void setUsername(String username) {
        this.username = username;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
