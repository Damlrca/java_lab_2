package org.example.java_lab;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class viewController implements IObserver {
    Model model = BModel.build();

    @FXML
    Pane viewPoints;

    @FXML
    public void initialize() {
        model.addObserver(this);
    }

    int port = 3124;
    InetAddress ip = null;
    SocketClient scl;

    @FXML
    void connect() {
        if (scl != null) return;

        try {
            Socket cs;
            ip = InetAddress.getLocalHost();
            cs = new Socket(ip, port);
            System.out.println("Client start");

            scl = new SocketClient(cs, false);

            scl.sendMsg(new Msg(null, MsgAction.GET));
        } catch (IOException ex) {
            System.out.println("Error in connect()");
        }
    }


    @FXML
    void mouseEvnt(MouseEvent evn) {
        if (scl != null) {
            ArrayList<Lab_2_Point> allp = new ArrayList<>();
            allp.add(new Lab_2_Point((int) evn.getX(), (int) evn.getY()));
            scl.sendMsg(new Msg(allp, MsgAction.ADD));
        } else {
            // m.add(new Point((int) evn.getX(), (int) evn.getY()));
        }
    }

    @Override
    public void event(Model model) {
        Platform.runLater(() -> {
            viewPoints.getChildren().removeAll();
            for (Lab_2_Point p : model) {
                Circle circle = new Circle(p.getX(), p.getY(), 10);
                circle.setFill(Color.RED);
                viewPoints.getChildren().add(circle);
            }
        });
    }
}
