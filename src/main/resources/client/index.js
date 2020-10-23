import { Terminal } from 'xterm';
import { AttachAddon } from 'xterm-addon-attach';
import './xterm.css'

const term = new Terminal();
const socket = new WebSocket('ws://localhost:8010');
const attachAddon = new AttachAddon(socket);

// Attach the socket to term
socket.onopen = function() {
    term.loadAddon(attachAddon);    
};

socket.onerror = function(e) {
    console.log(e);
};

term.open(document.getElementById('xterm-container'));