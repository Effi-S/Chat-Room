package com.web_course.chat_app;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is a simple chat app with a single chatroom.<br/>
 * Websocket was used to connect all users to one chatroom.<br/>
 * There are 2 Pages in this application: 1. Login Page 2. Chatroom Page.<br/>
 * The user logs in in the login page and can chat with any other user logged on.
 * 2 Exceptions can occur:
 *      1. The user tries to log in but a user with the same name is logged in.
 *      2. The user tries to access the chatroom before logging i =n
 *  In Both cases the user is redirected to the Login Page.
 */
@SpringBootApplication
public class ChatAppApplication {

    /**
     * The entry point of application.
     *
     * @param args Possible input arguments.
     */
    public static void main(String[] args) {
		SpringApplication.run(ChatAppApplication.class, args);
	}

}
