import { Terminal } from 'xterm';
import { AttachAddon } from 'xterm-addon-attach';

let connected = false;

// 연결 이벤트 설정
let btn = document.querySelector("#connect_btn");
    if(term){
        term.destroy();
    }
    
    const term = new Terminal();
    term.open(document.getElementById('xterm-container'));

    btn.addEventListener('click', function(event){
        if(connected == false){
            let host = document.getElementById("host").value;
            let port = document.getElementById("port").value;
            console.log(host + ":" + port);

            // const term = new Terminal();
            const socket = new WebSocket('ws://' + host + ":" + port);
            const attachAddon = new AttachAddon(socket);

            // Attach the socket to term
            socket.onopen = function() {
                term.loadAddon(attachAddon);
                connected = true;
            };

            socket.onerror = function(e) {
                console.log(e);
                alert("연결주소를 확인하고 다시 입력해주세요");
            };

            // term.open(document.getElementById('xterm-container'));
        }
});