package client;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    private String host;

    private int port;

    private String username;

    private boolean isValid;

    private String message;

    Scanner sc;

    Socket socket;

    DataInputStream in;

    DataOutputStream out;

    public Client(String host, int port) {

        this.host = host;

        this.port = port;

        sc = new Scanner(System.in);

        System.out.print("Username: ");

        username = sc.nextLine();

        isValid = verificationUsername(username);

        if (isValid) {
            try {
                socket = new Socket(host, port); // New socket with host and port

                in = new DataInputStream(socket.getInputStream()); // Data from Server

                out = new DataOutputStream(socket.getOutputStream()); // Data of client to server

                out.writeUTF(username); // Send username to server

                while (true) {
                    System.out.print("You: ");
                    message = sc.nextLine();

                    if (exit(message)) {
                        System.out.println("Disconnected");
                        socket.close();
                    }

                    out.writeUTF(username + ": " + message);

                    String messageServer = in.readUTF(); // Response by server
                    System.out.println("Server: " + messageServer); // Show response by server
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
            }
        } else {
            System.out.println("You have to introduce a valid username");
        }
    }

    /**
     * This function verify if the username introduced is valid
     * @param username
     * @return boolean variable who tells if the username is valid or no
     */
    private boolean verificationUsername(String username) {
        isValid = false;
        if (!username.isEmpty()) {
            isValid = true;
        }
        return isValid;
    }

    /**
     *
     * @param message
     * @return
     */
    public boolean exit(String message) {
        boolean exit = false;
        if (message.toLowerCase().equals("bye")) {
            exit = true;
        }
        return exit;
    }

    /**
     * Main
     * @param args
     */
    public static void main(String[] args) {
        Client client = new Client("localhost", 4999);
    }
}
