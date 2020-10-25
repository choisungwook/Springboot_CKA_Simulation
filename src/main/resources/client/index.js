import { Terminal } from 'xterm';
import { AttachAddon } from 'xterm-addon-attach';
import { FitAddon } from 'xterm-addon-fit';

let websock = null;
    
let term = new Terminal();
term.open(document.getElementById('xterm-container'));

let btn = document.querySelector("#connect_btn");
btn.addEventListener('click', function(event){
    if(websock == null){
        let host = document.getElementById("host").value;
        let port = document.getElementById("port").value;

        // const term = new Terminal();
        websock = new WebSocket('ws://' + host + ":" + port);

        // Attach the socket to term
        websock.onopen = function() {
            const fitAddon = new FitAddon();
            const attachAddon = new AttachAddon(websock);

            term.loadAddon(fitAddon);
            term.loadAddon(attachAddon);

            fitAddon.fit();
        };

        websock.onerror = function(e) {
            alert("연결주소를 확인하고 다시 입력해주세요");
            websock = null;
        };

        // term.open(document.getElementById('xterm-container'));
    }
});