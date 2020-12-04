package client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Client class
 */

public class Client extends Thread {

    private String host; // Server host

    private int port; // Server port

    private String username; // This variable contains the username

    private boolean isValid; // This variable verify if the username is valid

    private String message;

    private boolean exit = false;

    Scanner sc;

    Socket socket;

    DataInputStream in; // Data input

    DataOutputStream out; // Data output

    /**
     * Constructor
     * @param host
     * @param port
     */

    public Client(String host, int port) {
        this.host = host;
        this.port = port;
    }
    @Override
    public void run() {
            sc = new Scanner(System.in);

            System.out.print("Username: ");

            username = sc.nextLine(); // Stock the string introduced by user

            isValid = verificationUsername(username); // This variable verify if username is valid

            if (isValid) {
                try {
                    socket = new Socket(host, port); // New socket with host and port

                    in = new DataInputStream(socket.getInputStream()); // Data from Server

                    out = new DataOutputStream(socket.getOutputStream()); // Data of client to server

                    sendUsername(); // Send notification with username to server

                    while (true) { // Maintains the connection with the server

                        System.out.print("> ");
                        message = sc.nextLine();

                        if (exit(message)) { // Check if user wants to exit
                            disconnect();
                            break;
                        }

                        sendMessage(message);

                        if (readMessage()) {
                            break;
                        }
                    }

                } catch (IOException e) {
                    System.out.println("Server is down");
                }
            } else { // If user introduces nothing
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
        if (!username.isEmpty()) { // If username is not empty
            isValid = true;
        }
        return isValid;
    }

    /**
     * This function sends username to server
     */
    private void sendUsername(){
        try {
            out.writeUTF(username);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function disconnect user and close socket between client and server
     */
    private void disconnect() {
        try {
            out.close();
            in.close();
            socket.close();
            System.out.println("Disconnected");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function sends message to server
     */
    private void sendMessage(String message){
        try {
            out.writeUTF(username + ": " + message.trim()); // Send the message
            out.flush();
        } catch (IOException e) {
            System.out.println("Server is maybe down");
        }
    }

    /**
     * This function reads server message
     */
    private boolean readMessage() {
        try {
            String messageServer = in.readUTF();
            System.out.println("Server: " + messageServer.trim()); // Show response by server
        } catch (IOException e) {
            System.out.println("Server has stopped");
            exit = true;
        }
        return exit;
    }

    /**
     * This function verify if user want exit
     * @param message
     * @return
     */
    public boolean exit(String message) {
        if (message.toLowerCase().equals("bye")) {
            exit = true;
        }
        return exit;
    }

    /**
     * Main method
     * @param args
     */
    public static void main(String[] args) {
        Client client = new Client("localhost", 4999);
        client.start();
    }
}
