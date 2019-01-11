package org.nischay.java.util;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import org.nischay.java.entity.Client;

public class ClientHandler {

    List<Client> clients = new ArrayList<>();

    public void addClient(Client client) {
        clients.add(client);
    }

    public Client getByUserName(String userName) {
        for (Client c : clients) {
            if (c.getUserName().equals(userName)) {
                return c;
            }
        }
        return null;
    }

    public Client getBySocket(Socket socket) {
        for (Client c : clients) {
            if (c.getSocket().equals(socket)) {
                return c;
            }
        }
        return null;
    }

    public List<Client> getClients() {
        return clients;
    }

    public boolean removeClient(Client client) {
        return clients.remove(client);
    }

}
