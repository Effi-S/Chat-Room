// Set up WebSocket connection to "http://localhost:8080/stomp" as handshake entry-point.
const sock = new SockJS("http://localhost:8080/stomp", null, { timeout: 15000});

// Create a new StompClient object with the WebSocket endpoint.
const client = Stomp.over(sock);

// Takes the value in the ‘message-input’ text field and send it to the server.
function sendMessage(){
    // --1-- Get message from input area.
    const input = document.getElementById("message-input");
    const message = input.value;
    if(!message.trim()) return;

    // --2-- Get username from thymeleaf.
    const username_holder = document.getElementById("username-holder");
    const name = username_holder.innerText;
    console.log(`username: ${name} message: ${message}`)
    // --3-- send Json with 1 and 2
    client.send('/app/chat', {}, JSON.stringify({message: message, username: name}));

    // --3.2-- clearing input.
    input.value = "";
}

// Utility Function for refreshing the users.
// Users are loaded from the database and repopulate the user-list.
function refresh_users() {
    const user_list = document.getElementById('user-list');
    // -- a -- fetch users --
    const api_url = 'api/v1/users/get/all';
    fetch(api_url).then(response => {
        return response.json();
    }).then(data => {
        // -- b -- Clear user list.
        user_list.innerHTML = ''
        // -- c -- Reload the users.
        for(const elem of data){
            const new_user = document.createElement('p');
            new_user.appendChild(document.createTextNode(`${elem.username}`));
            new_user.className = "fas fa-user"
            user_list.appendChild(new_user);
        }
    }).catch(err => {
        console.warn(err);
    })

}

// Here we add the connection to the stomp endpoints
// Since we are connecting here we also will send
// A message declaring to everyone that we have entered the chat.
// The internal Fallback function of client.connect
// Is the function that occurs when a message is received
window.addEventListener('DOMContentLoaded', (event) => {

    client.connect({}, frame => {
        // --2-- Subscribe to "/topic/messages".
        client.subscribe("/topic/messages", payload => {
            const json_obj = JSON.parse(payload.body)
            if(["CONNECT", "DISCONNECT"].includes(json_obj.type)){
                refresh_users();
            }
            const message_list = document.getElementById('message-list');
            const li = document.createElement('li');
            li.appendChild(document.createTextNode(`${json_obj.username}: ${json_obj.message}`));
            message_list.appendChild(li);
        });
    });
});


// Added feature - Message sent when Enter pressed.
// This has the downside that the user can not send multi-line messages unless copy pasted.
window.addEventListener("keydown",
    function (e) {
    if (e.key === 'Enter') {
        sendMessage()
    }
});
