/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 var socket= new WebSocket("ws:127.0.0.1:8080/chat/hello");
 socket.onmessage = onMessage;
 
function myFunction() {
  var x;
x = document.getElementById("text").value;
socket.send(x);
}

function onMessage(event){
    var newMessage=event.data;
    alert(event.data);
}