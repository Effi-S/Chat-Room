package com.web_course.chat_app.message;

import javax.persistence.*;

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
    private Long uid;


    public Message(Long mid, String message, Long uid, String username) {

        this.mid = mid;
        this.message = message;
        this.username = username;
        this.uid = uid;
    }

    public Message() {

    }
    public Long getMid() {
        return mid;
    }
    public String getMessage() {
        return message;
    }
    public String getUsername() {
        return username;
    }
    public Long getUid() {
        return uid;
    }
    public void setUid(Long uid) {
        this.uid = uid;
    }
    public void setMid(Long mid) {
        this.mid = mid;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
