package com.web_course.chat_app.api.user;
import javax.persistence.*;
import java.time.Instant;

/**
 * The type User.<br />
 * This holds all information needed for a user.<br />
 * Users are stored in the "user" database.
 *
 */
@Entity
@Table
public class User{
    /** Id of the DB element. <br/>
     *  Generated automatically by Spring in sequential order**/
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
    /** The name of the user.**/
    private String username;


    /**
     * Instantiates a new User.
     *
     * @param username the username of the user to create.
     */
    public User(String username) {
        this.uid = Instant.now().toEpochMilli();
        this.username = username;
    }

    /**
     * Instantiates a new User.<br />
     * Blank constructor for Hibernate. <br />
     * This constructor should not be used otherwise.
     */
    public User(){
    }

    /**
     * Gets username.
     *
     * @return The username of the user to get.
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username The username to set.
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets user id.
     *
     * @return The user's id
     */
    public Long getId() {
        return uid;
    }
}
