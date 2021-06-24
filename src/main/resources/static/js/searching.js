// Here lies the code relevant to searching functionalities in the chatroom.
// The 2 searches are:
//      1. Searching for messages containing a sub-string/word.
//      2. Searching for messages by a particular user.

//List Element with our search results.
const search_list = document.getElementById("search-message-list")

//List Element with our messages.
const original_list = document.getElementById("message-list")

//The Button element that send messages - we want to shut this off when we do searches
const send_button = document.getElementById("message-send-button")

// -- -- -- Utility functions -- -- --
// Utility function for clearing our search list
function clear_search_list(){
    search_list.innerHTML = ''
}

// Utility function for adding messages to search list
function add_msg_lst_to_search(msg_lst) {
    if(msg_lst.length === 0){
        msg_lst.push({'username': 'chat-app', 'message': ' Nothing found :('})
    }
    for(const elem of msg_lst.values()){
        const li = document.createElement("li");
        li.innerText = `${elem.username}: ${elem.message}`
        search_list.append(li)
    }
}
// -- -- --

//function for when the is searching for a user.
function user_search(){
    clear_search_list()
    const username = document.getElementById("username-search").value
    const api_url = `api/v1/messages/find/user/${username}`;

    fetch(api_url).then(response => {
        return response.json();
    }).then(data => {
        add_msg_lst_to_search(data)
    }).catch(err => {
        console.warn(err);
    })

    original_list.hidden = true
    search_list.hidden = false
    send_button.disable = true
}

// function for when the user searches for a words / sub-string
function word_search(){
    clear_search_list()
    let word = document.getElementById("word-search").value
    let api_url = `api/v1/messages/find/sub_string/${word}`;

    fetch(api_url).then(response => {
        return response.json();
    }).then(data => {
        add_msg_lst_to_search(data)
    }).catch(err => {
        console.warn(err);
    })

    original_list.hidden = true
    search_list.hidden = false
    send_button.disable = true
}

// leaving the search results and returning to chat messages.
function back_to_messages(){
    original_list.hidden = false
    search_list.hidden = true
    send_button.disable = false
    clear_search_list()
}
