package org.example.java_lab;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class mainServer {
    private Model m = BModel.build();

    private int port = 3124;
    private InetAddress ip = null;

    public void StartServer() {
        ServerSocket ss;
        try {
            ip = InetAddress.getLocalHost();
            ss = new ServerSocket(port, 0, ip);
            System.out.println("Server started");
            while (true) {
                Socket socket = ss.accept();
                System.out.println("Client connected (" + socket.getPort() + ")");
                SocketClient scl = new SocketClient(socket, true);
                m.addObserver((model) -> {
                    Resp r = new Resp(model.getPoints());
                    scl.sendResp(r);
                });
            }
        } catch (IOException ex) {
            System.out.println("Error StartServer()");
        }
    }

    public static void main(String[] args) {
        mainServer server = new mainServer();
        server.StartServer();
    }
}
