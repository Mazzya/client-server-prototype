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

    ArrayList<Socket> lista;

    /**
     * Server port
     * @param port
     */

    public Server(int port) {

        this.port = port;
        this.lista = new ArrayList();

        try {
            sc = new Scanner(System.in);

            serverSocket = new ServerSocket(port); // Open server port

            System.out.println("Server started, listening...");

            while (true) {
                socket = serverSocket.accept();
                lista.add(socket);
                ThreadServer server = new ThreadServer(socket, serverSocket, lista.size());
                server.start();
                System.out.println(lista.size() + " users connected");

            }

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        Server server = new Server(4999);
    }
}
