package org.example.java_lab;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class Lab2_Controller {
    @FXML
    Pane main_pane;
    Lab2_Targets targets;

    public void initialize() {
        targets = new Lab2_Targets(main_pane);

        new Thread(() -> {
            try {
                while (true) {
                    Platform.runLater(() -> {
                        targets.nextTick();
                    });
                    Thread.sleep(100);
                }
            }
            catch (InterruptedException ie) {

            }
        }).start();
    }
}
