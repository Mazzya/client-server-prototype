package server;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {

    private int port;

    ServerSocket serverSocket;

    Socket socket;

    DataInputStream in;

    DataOutputStream out;

    Scanner sc;

    String messageServer;

    public Server(int port) {

        this.port = port;

        try {
            sc = new Scanner(System.in);

            serverSocket = new ServerSocket(port);

            System.out.println("Server started, listening...");

            socket = serverSocket.accept(); // Waiting connections

            in = new DataInputStream(socket.getInputStream());

            out = new DataOutputStream(socket.getOutputStream());

            String user = in.readUTF();

            System.out.println(user + " connected");

            while (true) {
                String message = in.readUTF(); // Reponse by client
                System.out.println(message);

                System.out.print("You: ");
                messageServer = sc.nextLine();

                out.writeUTF(messageServer); // Send response to client
            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Server server = new Server(4999);
    }
}
