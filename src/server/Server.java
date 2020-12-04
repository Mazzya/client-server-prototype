package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Server class
 */
public class Server {


    public int port; // Server port

    ServerSocket serverSocket;

    Socket socket; // Client socket

    Scanner sc;

    ArrayList<Socket> sockets_list;

    /**
     * Server port
     * @param port
     */

    public Server(int port) {

        this.port = port;
        this.sockets_list = new ArrayList();

        try {
            sc = new Scanner(System.in);

            serverSocket = new ServerSocket(port); // Open server port

            System.out.println("Server started, listening...");

            while (true) {
                socket = serverSocket.accept();
                sockets_list.add(socket);
                ThreadServer server = new ThreadServer(socket, serverSocket, sockets_list.size());
                server.start();
                System.out.println(sockets_list.size() + " users connected");

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Server server = new Server(4999);
    }
}
