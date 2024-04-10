package org.example.java_lab;

public class Lab2_Msg {
    private Lab2_MsgAction msgAction;

    public Lab2_Msg(Lab2_MsgAction msgAction) {
        this.msgAction = msgAction;
    }

    public Lab2_MsgAction getMsgAction() {
        return msgAction;
    }
}
