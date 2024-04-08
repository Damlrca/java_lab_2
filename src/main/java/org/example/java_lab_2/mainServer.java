package org.example.java_lab_2;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class mainServer {
    Model m = BModel.build();

    int port = 3124;
    InetAddress ip = null;

    void StartServer() {
        ServerSocket ss;
        Socket cs;

        m.add(new Point(10, 20));
        m.add(new Point(50, 50));

        try {
            ip = InetAddress.getLocalHost();
            ss = new ServerSocket(port, 0, ip);
            System.out.println("Server start");

            while (true) {
                cs = ss.accept();
                System.out.println("Client connect (" + cs.getPort() + ")");

                SocketClient scl = new SocketClient(cs, true);
                m.addObserver((model)->{
                    Resp r = new Resp(model.getPoints());
                    scl.sendResp(r);
                });
            }
        }
        catch (IOException ex) {
            System.out.println("Error");
        }
    }

    public static void main(String[] args) {
        mainServer ms = new mainServer();
        ms.StartServer();
    }
}
