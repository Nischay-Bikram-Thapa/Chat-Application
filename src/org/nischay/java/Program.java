package org.nischay.java;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import org.nischay.java.util.ClientHandler;
import org.nischay.java.util.ClientListener;

public class Program {

    public static void main(String[] args) {
        int port = 9000;
        try {
            ServerSocket server = new ServerSocket(port);
            System.out.println("Server is running at " + port);
            ClientHandler handler = new ClientHandler();
            while (true) {
                Socket socket = server.accept();
                System.out.println("Connection request from:" + socket.getInetAddress().getHostAddress());
                ClientListener listener = new ClientListener(socket,handler);
                listener.start();
            }
        } catch (IOException ioe) {
            System.out.println(ioe.getMessage());
        }

    }

}
