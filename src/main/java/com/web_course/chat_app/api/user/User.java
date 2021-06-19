package com.web_course.chat_app.api.user;
import javax.persistence.*;
import java.sql.Timestamp;
import java.time.Instant;

@Entity
@Table
public class User{

    @Id
    @SequenceGenerator(
           name = "user_sequence",
           sequenceName = "user_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    private Long uid;
    private String username;
    private String startTime;


    public User(String username) {
        this.uid = Instant.now().toEpochMilli();
        this.username = username;
    }
    public User(){
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isActive() {
        return true;
    }

    public void startActivity(){
        this.startTime = Timestamp.from(Instant.now()).toString();
    }

    public Long getId() {
        return uid;
    }
}
