package org.example.java_lab;

import javafx.scene.paint.Color;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Lab2_Server {
    private static Lab2_Model model = Lab2_BModel.getModel();

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
                Lab2_SocketClient scl = new Lab2_SocketClient(socket, true);
                model.addObserver((model) -> {
                    Lab2_Resp r = new Lab2_Resp(Lab2_RespAction.GAME_STATE, model.getGameState());
                    scl.sendResp(r);
                });
            }
        } catch (IOException ex) {
            System.out.println("Error StartServer()");
        }
    }

    public static void main(String[] args) {
        model.getGameState().getPlayers().add(new Lab2_Player("player1", 45, 500.0 / 3, "#28d622"));
        model.getGameState().getPlayers().add(new Lab2_Player("player2", 45, 500.0 / 3 * 2, "#bc8f8f"));
        Thread gameThread = new Thread(()->{
            while (true) {
                model.nextTick();
                model.getGameState().getPlayers().getFirst().newBullet();
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    System.out.println("Thread.sleep(100) thrown");
                }
            }
        });
        gameThread.setDaemon(true);
        gameThread.start();
        Lab2_Server server = new Lab2_Server();
        server.StartServer();
    }
}
