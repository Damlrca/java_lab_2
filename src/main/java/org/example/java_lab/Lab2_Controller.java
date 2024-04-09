package org.example.java_lab;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class Lab2_Controller {
    @FXML
    Pane main_pane;
    Lab2_Targets targets;
    Lab2_Player player;

    public void initialize() {
        targets = new Lab2_Targets(main_pane);
        player = new Lab2_Player("player", 20, 250, main_pane);

        new Thread(() -> {
            try {
                while (true) {
                    Platform.runLater(() -> {
                        targets.nextTick();
                        player.nextTick(targets);
                    });
                    Thread.sleep(100);
                }
            }
            catch (InterruptedException ie) {

            }
        }).start();
    }

    @FXML
    public void fire() {
        player.newBullet();
    }
}
