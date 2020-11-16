# Chat | Client - Server prototype
## A simple and functional chat between client and server.
#### This prototype has been developed to allow people to understand how a connection between a client and a server works in Java with sockets. The prototype works, the address that is sent to the client to connect is the local address "localhost" or "127.0.0.1" and the port is "4999", you can change those connection data if you wish. There are 2 .jar executables, the server and the client. The server will always listen for a connection, when the client identifies with its user, the connection will be made with the server and the latter will notify you through the console. From here, the client can interact with the server through direct messages. The order of sending messages is as follows: first the client, second the server. I hope it serves as an example. Finally, this example has been developed for educational purposes, I am not responsible for its misuse.

### How to execute jar executables ?
#### You have to open your console and navigate to the directory that contains the executables and type the following command: `java -jar jarName`
#### Remember that you must have the JVM installed to be able to execute the executables correctly
