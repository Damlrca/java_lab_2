package org.example.java_lab;

import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;

public class Lab2_SocketClient {
    private final Lab2_Model model = Lab2_BModel.getModel();
    private static final Gson gson = new Gson();
    private final Socket socket;
    private InputStream is;
    private OutputStream os;
    private DataInputStream dis;
    private DataOutputStream dos;
    private final boolean isServer;

    private final Lab2_IObserver serverObserver = (model) -> {
        Lab2_Resp r = new Lab2_Resp(Lab2_RespAction.GAME_STATE, model.getGameState());
        this.sendResp(r);
    };
    private String playerName;
    private boolean successfullyConnected = false;

    public Lab2_SocketClient(Socket socket, boolean isServer) {
        this.socket = socket;
        this.isServer = isServer;

        try {
            os = socket.getOutputStream();
            dos = new DataOutputStream(os);
            is = socket.getInputStream();
            dis = new DataInputStream(is);
        } catch (IOException e) {
            System.out.println("Error SocketClient(Socket cs)");
        }

        Thread socketClientThread = new Thread(() -> {
            run();
        });
        socketClientThread.setDaemon(true);
        socketClientThread.start();
    }

    private void run() {
        while (true) {
            if (isServer) {
                Lab2_Msg msg = readMsg();
                if (msg == null) {
                    System.out.println("client (" + socket.getPort() + ") disconnected (or error thrown)");
                    model.removeObserver(serverObserver);
                    break;
                }
                if (successfullyConnected) {
                    if (msg.getMsgAction() == Lab2_MsgAction.FIRE) {
                        model.fire(playerName);
                    }
                    if (msg.getMsgAction() == Lab2_MsgAction.PAUSE) {
                        model.setPause(playerName);
                    }
                    if (msg.getMsgAction() == Lab2_MsgAction.READY) {
                        model.setReady(playerName);
                    }
                } else {
                    if (msg.getMsgAction() == Lab2_MsgAction.LOGIN) {
                        playerName = msg.getPlayerName();
                        synchronized (Lab2_BModel.getGameStateMutex()) {
                            if (model.tryAddPlayer(playerName)) {
                                successfullyConnected = true;
                                sendResp(new Lab2_Resp(Lab2_RespAction.LOGIN_OK, model.getGameState()));
                            }
                        }
                    }
                }
            } else {
                Lab2_Resp r = readResp();
                if (r == null) {
                    System.out.println("server disconnected (or error thrown)");
                    break;
                }
                if (successfullyConnected) {
                    model.setGameState(r.getGameState());
                } else {
                    if (r.getRespAction() == Lab2_RespAction.LOGIN_OK) {
                        successfullyConnected = true;
                        model.setGameState(r.getGameState());
                    }
                }
            }
        }
    }

    public void sendResp(Lab2_Resp resp) {
        String srtResp = gson.toJson(resp);
        try {
            dos.writeUTF(srtResp);
        } catch (IOException e) {
            System.out.println("Error void sendResp(Lab2_Resp resp)");
        }
    }

    public Lab2_Resp readResp() {
        Lab2_Resp r = null;
        try {
            String respStr = dis.readUTF();
            r = gson.fromJson(respStr, Lab2_Resp.class);
        } catch (IOException e) {
            System.out.println("Error Lab2_Resp readResp()");
        }
        return r;
    }

    public void sendMsg(Lab2_Msg msg) {
        String strMsg = gson.toJson(msg);
        try {
            dos.writeUTF(strMsg);
        } catch (IOException e) {
            System.out.println("Error void sendMsg(Lab2_Msg msg)");
        }
    }

    public Lab2_Msg readMsg() {
        Lab2_Msg msg = null;
        try {
            String msgStr = dis.readUTF();
            msg = gson.fromJson(msgStr, Lab2_Msg.class);
        } catch (IOException e) {
            System.out.println("Error readMsg()");
        }
        return msg;
    }

    public Lab2_IObserver getServerObserver() {
        return serverObserver;
    }

    public boolean isSuccessfullyConnected() {
        return successfullyConnected;
    }
}
