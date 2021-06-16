package com.web_course.chat_app.api.user;

import org.springframework.web.context.request.RequestContextHolder;

import javax.persistence.*;

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
    private String session;
    private String username;
    private String password;


    public User(String username, String password) {
        this.username = username;
        this.password = password;
        this.session = loadSession();
    }
    public User(){

    }

    private String loadSession(){
        return RequestContextHolder.currentRequestAttributes().getSessionId();
    }

    public String getSession() {
        return session;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public void setPassword(String password) {
        this.password = password;
    }


    public void setSession( String session) {
        this.session = session;
    }
}
