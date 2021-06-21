// Try to set up WebSocket connection with the handshake at "http://localhost:8080/stomp"
const sock = new SockJS("http://localhost:8080/stomp", null, { timeout: 15000});
// Create a new StompClient object with the WebSocket endpoint
const client = Stomp.over(sock);
const session_id = /SESS\w*ID=([^;]+)/i.test(document.cookie) ? RegExp.$1 : false;
// Start the STOMP communications, provide a callback for when the CONNECT frame arrives.


// Take the value in the ‘message-input’ text field and send it to the server with empty headers.
function sendMessage(){

    // --1-- Get message from input area
    const input = document.getElementById("message-input");
    const message = input.value;
    console.log("!message.trim(): " + !message.trim())
    if(!message.trim()) return;

    // --2-- Get username from thymeleaf
    const username_holder = document.getElementById("username-holder");
    const name = username_holder.innerText;
    console.log(`username: ${name} message: ${message}`)
    // --3-- send Json with 1 and 2
    client.send('/app/chat', {}, JSON.stringify({message: message, username: name}));

    // --3.2-- clearing input.
    input.value = "";
}


window.addEventListener('DOMContentLoaded', (event) => {

    client.connect({}, frame => {

        // --1--- Add username to connected users (/app/chat.new-user).
        const username_holder = document.getElementById("username-holder");
        const name = username_holder.innerText;
        client.send('/app/new-user', {}, JSON.stringify({username: name, type: 'CONNECT'}));

        // --2-- Subscribe to "/topic/messages".
        client.subscribe("/topic/messages", payload => {
            const json_obj = JSON.parse(payload.body)
            if(json_obj.type === "CONNECT"){
                const user_list = document.getElementById('user-list');
                const new_user = document.createElement('li');
                new_user.appendChild(document.createTextNode(`${json_obj.username}`));
                user_list.appendChild(new_user);

            } else if (json_obj.type === "DISCONNECT"){
                const user_lst = document.getElementById('user-list');
                for (const elem of user_lst.children) {
                    if (elem.innerText === `${json_obj.username}`)
                        elem.parentNode.removeChild(elem);
                }
            } else {
                const message_list = document.getElementById('message-list');
                const message = document.createElement('li');

                message.appendChild(document.createTextNode(`${json_obj.username}: ${json_obj.message}`));
                message_list.appendChild(message);
            }
        });
    });
});
