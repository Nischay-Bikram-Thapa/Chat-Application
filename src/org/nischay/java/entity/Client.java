
package org.nischay.java.entity;

import java.net.Socket;


public class Client {
private Socket socket;
private String userName;
private String password;

    public Client(Socket socket, String userName, String password) {
        this.socket = socket;
        this.userName = userName;
        this.password = password;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
 
}
