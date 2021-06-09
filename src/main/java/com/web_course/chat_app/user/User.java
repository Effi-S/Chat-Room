package com.web_course.chat_app.user;

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
    Long id;
    String name;
    String password;

    public User(Long id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }

    public User() {

    }

    public User(long id, String name) {
    }

    public User(User user) {
       this.id = user.getId();
       this.name = user.getName();
       this.password = user.getPassword();
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }
}
