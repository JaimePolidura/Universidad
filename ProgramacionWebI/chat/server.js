import express from 'express';
import http from 'http';
import { Server } from 'socket.io';

const app = express();
const server = http.createServer(app);
const io = new Server(server);

io.on('connection', (socket) => {
  connections.push(socket);

  io.on("broadcast", message => {
    io.emit("message", message);
  });
  io.on("send", (message, recipientId) => {
    io.to(recipientId).emit("message", message);
  });
});

server.listen(3000, () => {
  console.log('Server is listening on port 3000');
});

app.post("/login", (req ,res) => {
  const username = req.body["username"];
  const password = req.body["password"];

  const authenticatd = authenticate(username, password);
  
  if(!authenticatd){
    res.send(403, "Usuario incorrecto");
  } else {
    res.send(200, "Ok");
  }
});

function authenticate(username, password) {
  return true;
}