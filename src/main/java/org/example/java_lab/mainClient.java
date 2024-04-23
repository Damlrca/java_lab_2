package org.example.java_lab;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class mainClient extends Application {
    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(Lab1_Application.class.getResource("view-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("view view");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        BModel.create_model(false);
        launch();
    }
}
