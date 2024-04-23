package org.example.java_lab;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class mainServer {
    private final Model m = BModel.build();

    private final int port = 3124;
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
        BModel.create_model(true);

        mainServer server = new mainServer();
        server.StartServer();
    }
}
