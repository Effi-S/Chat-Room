const search_list = document.getElementById("search-message-list")
const original_list = document.getElementById("message-list")
const send_button = document.getElementById("message-send-button")


function clear_search_list(){
    for(const elem of search_list.children)
        search_list.removeChild(elem)
}

function add_msgs_to_search(msgs) {
    if(msgs.length === 0){
        msgs.appa = [{'username': 'chat-app', 'message': 'Nothing found'}]
    }
    for(const elem of msgs.values()){
        const li = document.createElement("li");
        li.innerText = `${elem.username}: ${elem.message}`
        search_list.append(li)
    }

}

function user_search(){
    clear_search_list()
    const username = document.getElementById("username-search").value
    const api_url = `api/v1/messages/find/user/${username}`;

    fetch(api_url).then(response => {
        return response.json();
    }).then(data => {
        add_msgs_to_search(data)
    }).catch(err => {
        console.warn(err);
    })

    original_list.hidden = true
    search_list.hidden = false
    send_button.disable = true
}


function word_search(){
    clear_search_list()
    let word = document.getElementById("word-search").value
    let api_url = `api/v1/messages/find/sub_string/${word}`;

    fetch(api_url).then(response => {
        return response.json();
    }).then(data => {
        add_msgs_to_search(data)
    }).catch(err => {
        console.warn(err);
    })

    original_list.hidden = true
    search_list.hidden = false
    send_button.disable = true
}

function back_to_messages(){
    original_list.hidden = false
    search_list.hidden = true
    send_button.disable = false
    clear_search_list()
}
