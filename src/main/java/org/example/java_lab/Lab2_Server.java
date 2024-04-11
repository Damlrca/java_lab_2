package org.example.java_lab;

import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Lab2_Server {
    private static Lab2_Model model = Lab2_BModel.getModel();
    private static Object gameThreadMutex = new Object();
    private static Thread gameThread = new Thread(() -> {
        try {
            while (true) {
                if (model.getGameState().getGameStatus() == Lab2_GameStatus.ONGOING) {
                    boolean finalTick = false;
                    synchronized (model.getGameState()) {
                        model.nextTick();

                        Lab2_GameState gameState = model.getGameState();
                        int mx = -1, count_mx = 0;
                        for (Lab2_Player player : gameState.getPlayers()) {
                            if (player.getCountScore() > mx) {
                                mx = player.getCountScore();
                                count_mx = 1;
                            } else if (player.getCountScore() == mx) {
                                count_mx++;
                            }
                        }
                        if (mx >= 6 && count_mx == 1) {
                            String playerName = "";
                            for (Lab2_Player player : gameState.getPlayers()) {
                                if (player.getCountScore() == mx)
                                    playerName = player.getPlayerName();
                            }
                            model.resetGame("\nПобедитель: " + playerName + "\n\nДля начала следующей\nигры нажмите 'Готов'");
                            finalTick = true;
                        }
                    }
                    if (!finalTick) {
                        Thread.sleep(100);
                    }
                } else {
                    synchronized (gameThreadMutex) {
                        gameThreadMutex.wait();
                    }
                }
            }
        } catch (InterruptedException e) {
            System.out.println("gameThread thrown");
        }
    });

    public static void wakeUpGameThread() {
        synchronized (gameThreadMutex) {
            gameThreadMutex.notifyAll();
        }
    }

    private static int port = 3124;
    private static InetAddress ip = null;

    public static void StartServer() {
        ServerSocket ss;
        try {
            ip = InetAddress.getLocalHost();
            ss = new ServerSocket(port, 0, ip);
            System.out.println("Server started");
            while (true) {
                Socket socket = ss.accept();
                System.out.println("Client connected (" + socket.getPort() + ")");
                Lab2_SocketClient scl = new Lab2_SocketClient(socket, true);
                Lab2_IObserver observer = scl.getServerObserver();
                model.addObserver(observer);
            }
        } catch (IOException ex) {
            System.out.println("Error StartServer()");
        }
    }

    public static void main(String[] args) {
        gameThread.setDaemon(true);
        gameThread.start();

        StartServer();
    }
}
