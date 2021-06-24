Spring Web development Exercise 4.
Chat Application.
README
Author - Efrayim Sztokman

Date: 24/06/2021
ID - 315704130
Email - Efrayimsz@edu.hac.ac.il

See Javadoc

Program overview

This is a simple chat app with a single chatroom.
Websocket was used to connect all users to one chatroom.
There are 2 Pages in this application: 1. Login Page 2. Chatroom Page.
The user logs in in the login page and can chat with any other user logged on.
This Application instantiates 2 Databases:
1. User Database - for storing all connected users with their session ID's
2. Message Database 0 for storing all messages sent in the chat.
When the user logs in:
1. The username is passed to the API from the login form.
2. The API triggers creating the User element in the User database.
3. The user is then redirected to the chatroom page.
4. When entering the chatroom the user subscribes to the websocket endpoint.
Now when any message is sent the client reads and executes showing the message.
3 Exceptions can occur during runtime:
1. The user tries to log in but a user with the same name is logged in.
2. The user tries to access the chatroom before logging in
3. The user tries to log in while still connected to the chat in the same session.
In All three cases the user is redirected to the Login Page.
And the relevant error message is displayed on the top of the page using thyeme-leaf.
________________________________________
Code Structure
API
The api package is the REST API for connecting to each Database, "user" and "message".
For the user Database there is the internal user package
and for message there is the message package.
message package
Message
Message holds all information about a message.
Messages are stored in "message" table in DB.
MessageConfig
In this class we can instantiate our Massage DB.
MessageController
This acts as the endpoint to our Messages API.
MessageService
This is the Service Layer of "message" DB.
MessageRepository
This is the repository layer for our message database.
MassageType
This is an Enum class for determining the type of message that was sent.
Based on a messages type, the front end can decide how to react.
user package
User
User holds all information about a User.
Users are stored in "user" table in DB.
UserConfig
In this class we can instantiate our User DB.
UserController
This acts as the endpoint to our User API.
UserService
This is the Service Layer of "user" DB.
UserRepository
This is the repository layer for our user database.
controllers Package
Here we hold our controllers that are not part of the REST API.
PageController
This class controls HTTP Request mapping of our app.
This include: login, logout and chatroom.
ChatController
This Class is in charge of mapping messages sent by WebSocket.
web_socket
Here we hold all of the server side logic for instantiating our Websocket protocol.
WebSocketConfig
This class configures our STOMP endpoints.
Connects to Event listeners and Handshake interceptors.
WebSocketEventListener
This class Handles Websocket Events.
Custom Handles for CONNECT and DISCONNECT exist here.
HttpHandshakeInterceptor

This class intercepts the WebSocket handshake.
Here session attributes are added to STOMP protocol.
As well, in the event of refreshing the page, we make sure the user is not removed from the database.
WebSocketAuthInterceptor
Web socket authentication interceptor.
This interceptor Class *can be* used to validate the messages being passed.
Unfortunately, no authentication was implemented in this project so this has no use to us.
exception Package
Here we store various Custom Runtime Exceptions.
As well, we have a Exception handler for these Exceptions.


UserExceptionHandler
This ControllerAdvice is in charge of routing all User related exceptions.
UserAlreadyRegisteredException
Instantiates a new User already registered exception.
UserNotExistException
nstantiates a new User not exist exception.



Thymeleaf design.
1. default.html - All Thyemeleaf templates must derive their layouts from default.html
2. fragments.html Various fragments exist in fragments.html that can be used by multiple templates.
Thymleaf Templates
1. chat-client.html - The chatroom.
2. login.html - The
JavaScript
•	chat-client.js:.

In this JS file we connect to the Websocket from th clients side.
And hold all of the client-side logic for sending and receiving messages using
•	searching.js:.

•	Here lies the code relevant to searching functionalities in the chatroom.
•	The 2 searches are:
•		 1. Searching for messages containing a sub-string/word.
•		 1. Searching for messages by a particular user.
			    
CSS
css for all pages can be found in styels.css
UML


