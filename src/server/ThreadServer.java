package server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;

public class ThreadServer extends Thread {
    Socket socket;
    ServerSocket serverSocket;
    private ArrayList<Socket> clients;
    DataInputStream in;
    DataOutputStream out;
    Scanner sc;
    private int nClients;

    public ThreadServer(Socket socket, ServerSocket serverSocket, int nClients) {
        this.socket = socket;
        this.serverSocket = serverSocket;
        this.clients = new ArrayList();
        this.nClients = nClients;
    }


    @Override
    public void run() {
        String user = null;
        try {

            sc = new Scanner(System.in);

            in = new DataInputStream(socket.getInputStream()); // Retrieve the data from the stream using the client socket.

            out = new DataOutputStream(socket.getOutputStream()); // Send stream data using client socket.

            user = in.readUTF(); // This variable contains client username

            System.out.println(user + " connected " + socket.getInetAddress());

            clients.add(socket);

            while (true) {
                String message = in.readUTF(); // This variable contains client message
                System.out.println(message); // Show variable message
                System.out.print("Root : ");
                String messageServer = sc.nextLine().trim(); // This variable contains server message
                out = new DataOutputStream(socket.getOutputStream()); // For each client, creates new DataOutputStream
                out.writeUTF(messageServer);
                out.flush();
            }

        } catch (IOException e) {
            System.out.println(user + " logged out");
        }
    }

    public ArrayList<Socket> getClients() {
        return clients;
    }
}
