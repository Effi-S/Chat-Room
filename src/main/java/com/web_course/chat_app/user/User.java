package com.web_course.chat_app.user;

import org.springframework.web.context.request.RequestContextHolder;

import javax.persistence.*;

@Entity
@Table
public class User {
    @Id
    @SequenceGenerator(name="user_sequence",
            sequenceName = "user_sequence",
            allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "user_sequence"
    )
    String id;
    String name;
    String password;
    Boolean active;

    public User(String name, String password) {
        setSession();
        this.name = name;
        this.password = password;
        active = true;
    }

    public void setSession(){
        this.id = RequestContextHolder.currentRequestAttributes().getSessionId();
    }
    public User() {
    }

    void setActivity(Boolean activity){
        this.active = activity;
    }
    public User(User user) {
       this.id = user.getId();
       this.name = user.getName();
       this.password = user.getPassword();
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
