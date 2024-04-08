package org.example.java_lab_2;

import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;

public class SocketClient {
    Model m = BModel.build();
    Gson gson = new Gson();
    Socket cs;
    InputStream is;
    OutputStream os;
    DataInputStream dis;
    DataOutputStream dos;
    boolean isServer = true;

    public SocketClient(Socket cs, boolean isServer) {
        this.cs = cs;
        this.isServer = isServer;

        try {
            os = cs.getOutputStream();
            dos = new DataOutputStream(os);
        } catch (IOException e) {
            System.out.println("Error SocketClient(Socket cs)");
        }

        new Thread(()->{run();}).start();
    }

    void run() {
        try {
            is = cs.getInputStream();
            dis = new DataInputStream(is);
        } catch (IOException e) {
            System.out.println("Error run()");
        }

        while (true) {
            if (isServer) {
                Msg msg = readMsg();
                if (msg.getAction() == MsgAction.ADD) {
                    for (Point p : msg.getPoints()) {
                        m.add(p);
                    }
                }
                if (msg.getAction() == MsgAction.GET) {
                    Resp resp = new Resp(m.getPoints());
                    sendResp(resp);
                }
            }
            else {
                Resp r = readResp();
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
        String respMsg = gson.toJson(resp);
        try {
            dos.writeUTF(respMsg);
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
