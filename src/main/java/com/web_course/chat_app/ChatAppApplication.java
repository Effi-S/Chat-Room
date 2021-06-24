package com.web_course.chat_app;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**<pre>
 * This is a simple chat app with a single chatroom.
 * Websocket was used to connect all users to one chatroom.
 * There are 2 Pages in this application: 1. Login Page 2. Chatroom Page.
 * The user logs in in the login page and can chat with any other user logged on.
 *
 * This Application instantiates 2 Databases:
 *      1. User Database - for storing all connected users with their session ID's
 *      2. Message Database 0 for storing all messages sent in the chat.
 *      3. The user is then redirected to the chatroom page.
 *      4. When entering the chatroom the user subscribes to the websocket endpoint.
 * 	        Now when any message is sent the client reads and executes showing the message.
 *
 * When the user logs in:
 * 	  1. The username is passed to the API from the login form.
 * 	  2. The API triggers creating the User element in the User database.
 * 	  3. The user is then redirected to the chatroom page.
 *
 * 3 Exceptions can occur during runtime:
 *      1. The user tries to log in but a user with the same name is logged in.
 *      2. The user tries to access the chatroom before logging in
 *      3. The user tries to log in while still connected to the chat in the same session.
 *  In All three cases the user is redirected to the Login Page.
 *  And the relevant error message is displayed on the top of the page using thyeme-leaf.
 *  </pre>
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
