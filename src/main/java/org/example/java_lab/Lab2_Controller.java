package org.example.java_lab;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;

public class Lab2_Controller {
    private Lab2_Model model = Lab2_BModel.getModel();

    private Lab2_ClientGameState clientGameState = new Lab2_ClientGameState();

    @FXML
    Pane main_pane;

    @FXML
    TextField textField_name;

    public void initialize() {
        model.addObserver((model) -> {
            Platform.runLater(() -> {
                clientGameState.removeFromPane(main_pane);
                clientGameState.update(model.getGameState());
                clientGameState.addToPane(main_pane);
            });
        });
    }

    @FXML
    public void fire() {
        if (scl != null) {
            scl.sendMsg(new Lab2_Msg(Lab2_MsgAction.FIRE, null));
        }
    }

    private int port = 3124;
    private InetAddress ip = null;
    private Lab2_SocketClient scl = null;

    @FXML
    public void connect() {
        if (scl != null) return;
        try {
            ip = InetAddress.getLocalHost();
            Socket socket = new Socket(ip, port);
            System.out.println("Client start");

            scl = new Lab2_SocketClient(socket, false);

            scl.sendMsg(new Lab2_Msg(Lab2_MsgAction.NAME_TO_LOGIN, textField_name.getText()));
        } catch (IOException ex) {
            System.out.println("Error in connect()");
        }
    }

}
