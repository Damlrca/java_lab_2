package org.example.java_lab_2;

import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;

public class SocketClient {
    private Model m = BModel.build();
    private Gson gson = new Gson();
    private Socket socket;
    private InputStream is;
    private OutputStream os;
    private DataInputStream dis;
    private DataOutputStream dos;
    private boolean isServer = true;

    public SocketClient(Socket socket, boolean isServer) {
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
                Msg msg = readMsg();
                if (msg == null) {
                    System.out.println("client (" + socket.getPort() + ") disconnected (or error thrown)");
                    break;
                }
                if (msg.getAction() == MsgAction.ADD) {
                    m.add(msg.getPoints());
                }
                if (msg.getAction() == MsgAction.GET) {
                    sendResp(new Resp(m.getPoints()));
                }
            } else {
                Resp r = readResp();
                if (r == null) {
                    System.out.println("server disconnected (or error thrown)");
                    break;
                }
                m.set(r.getPoints());
            }
        }
    }

    public Resp readResp() {
        Resp r = null;
        try {
            String respStr = dis.readUTF();
            r = gson.fromJson(respStr, Resp.class);
        } catch (IOException e) {
            System.out.println("Error readResp()");
        }
        return r;
    }

    public Msg readMsg() {
        Msg m = null;
        try {
            String msgStr = dis.readUTF();
            m = gson.fromJson(msgStr, Msg.class);
        } catch (IOException e) {
            System.out.println("Error readMsg()");
        }
        return m;
    }

    public void sendResp(Resp resp) {
        String srtResp = gson.toJson(resp);
        try {
            dos.writeUTF(srtResp);
        } catch (IOException e) {
            System.out.println("Error sendResp(Resp resp)");
        }
    }

    public void sendMsg(Msg msg) {
        String strMsg = gson.toJson(msg);
        try {
            dos.writeUTF(strMsg);
        } catch (IOException e) {
            System.out.println("Error sendMsg(Msg msg)");
        }
    }
}
