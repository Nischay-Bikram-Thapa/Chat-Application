package org.nischay.java.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import org.nischay.java.entity.Client;

public class ClientListener extends Thread {

    private Client client;
    private Socket socket;
    private ClientHandler handler;
    private PrintStream out;
    private BufferedReader reader;

    public ClientListener(Socket socket, ClientHandler handler) throws IOException {
        this.socket = socket;
        this.handler = handler;
        out = new PrintStream(socket.getOutputStream());
        reader = new BufferedReader(
                new InputStreamReader(socket.getInputStream()));
    }

    @Override
    public void run() {
        try {
            login();
            while (!isInterrupted()) {
                out.println("(Me)->");
                String line = reader.readLine();
                if (line.equalsIgnoreCase("list")) {
                    StringBuilder content = new StringBuilder();
                    content.append("Listing Users..............\r\n");
                    for (Client c : handler.getClients()) {
                        content.append(c.getUserName()).append("\r\n");
                    }
                    out.println(content.toString());
                } else if (line.equalsIgnoreCase("exit")) {
                    socket.close();
                    handler.removeClient(client);
                    broadcastMessage(client," has left the ChatRoom");
                 }else{
                    String msg = client.getUserName() + " says-> " + line;
                broadcastMessage(client, msg);
                }
                
            }

        } catch (IOException ie) {
            System.out.println(ie.getMessage());
        }
    }

    private void broadcastMessage(Client client, String msg) throws IOException {
        for (Client c : handler.getClients()) {
            if (!c.equals(client)) {
                PrintStream os = new PrintStream(c.getSocket().getOutputStream());
                os.println(msg);
            }
        }
    }

    private void login() throws IOException {
        out.println("Welcome to the ChatApp");
        out.println("Enter your User Name");
        String userName = reader.readLine();
        out.println("Enter your password");
        String password = reader.readLine();
        System.out.println("User " + userName + " connected");
        client = new Client(socket, userName, password);
        handler.addClient(client);
        broadcastMessage(client, userName +" has joined the ChatRoom");
    }

}
