package org.example.java_lab;

import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;

public class Lab2_SocketClient {
    private Lab2_Model model = Lab2_BModel.getModel();
    private Gson gson = new Gson();
    private Socket socket;
    private InputStream is;
    private OutputStream os;
    private DataInputStream dis;
    private DataOutputStream dos;
    private boolean isServer = true;

    public Lab2_SocketClient(Socket socket, boolean isServer) {
        this.socket = socket;
        this.isServer = isServer;

        try {
            os = socket.getOutputStream();
            dos = new DataOutputStream(os);
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
        try {
            is = socket.getInputStream();
            dis = new DataInputStream(is);
        } catch (IOException e) {
            System.out.println("Error run()");
        }

        while (true) {
            if (isServer) {
//                Msg msg = readMsg();
//                if (msg == null) {
//                    System.out.println("client (" + socket.getPort() + ") disconnected (or error thrown)");
//                    break;
//                }
//                if (msg.getAction() == MsgAction.ADD) {
//                    m.add(msg.getPoints());
//                }
//                if (msg.getAction() == MsgAction.GET) {
//                    sendResp(new Resp(m.getPoints()));
//                }

            } else {
                Lab2_Resp r = readResp();
                if (r == null) {
                    System.out.println("server disconnected (or error thrown)");
                    break;
                }
                model.setGameState(r.getGameState());
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
}
