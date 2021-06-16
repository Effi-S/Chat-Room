// Try to set up WebSocket connection with the handshake at "http://localhost:8080/stomp"
const sock = new SockJS("http://localhost:8080/stomp");

// Create a new StompClient object with the WebSocket endpoint
const client = Stomp.over(sock);
const session_id = /SESS\w*ID=([^;]+)/i.test(document.cookie) ? RegExp.$1 : false;
// Start the STOMP communications, provide a callback for when the CONNECT frame arrives.



// Take the value in the ‘message-input’ text field and send it to the server with empty headers.
function sendMessage(){

    const input = document.getElementById("message-input");
    const message = input.value;

    client.send('/app/chat', {}, JSON.stringify({message:  message}));

}


window.addEventListener('DOMContentLoaded', (event) => {
    console.log("dom content loaded");
    client.connect({}, frame => {
        // Subscribe to "/topic/messages".
        client.subscribe("/topic/messages", payload => {
            console.log(`entered: ${frame}`)
            let message_list = document.getElementById('message-list');
            let message = document.createElement('li');

            message.appendChild(document.createTextNode(JSON.parse(payload.body).message));
            message_list.appendChild(message);

        });
    });
});
