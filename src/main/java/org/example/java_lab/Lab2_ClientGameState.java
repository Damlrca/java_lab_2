package org.example.java_lab;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.*;

import java.util.ArrayList;

public class Lab2_ClientGameState {
    private ArrayList<Shape> shapes = new ArrayList<>();

    public Lab2_ClientGameState() {
    }

    Lab2_ClientGameState(Lab2_GameState gameState) {
        update(gameState);
    }

    void update(Lab2_GameState gameState) {
        shapes.clear();
        for (Lab2_Target target : gameState.getTargets()) {
            Circle circle = new Circle();
            circle.setLayoutX(target.getPosX());
            circle.setLayoutY(target.getPosY());
            circle.setRadius(target.getRadius());
            circle.setFill(Color.DEEPSKYBLUE);
            circle.setStroke(Color.BLACK);
            shapes.add(circle);
        }
        for (Lab2_Player player : gameState.getPlayers()) {
            Polygon polygon = new Polygon(-25.0, -50.0, 0.0, 0.0, -25.0, 50.0);
            polygon.setFill(Paint.valueOf(player.getPlayerColor()));
            polygon.setStroke(Color.BLACK);
            polygon.setLayoutX(player.getPlayerX());
            polygon.setLayoutY(player.getPlayerY());
            shapes.add(polygon);
            for (Point bullet : player.getBullets()) {
                Rectangle rectangle = new Rectangle();
                rectangle.setHeight(10.0);
                rectangle.setWidth(10.0);
                rectangle.setLayoutX(bullet.getX() - 5.0);
                rectangle.setLayoutY(bullet.getY() - 5.0);
                rectangle.setFill(Paint.valueOf(player.getPlayerColor()));
                rectangle.setStroke(Color.BLACK);
                shapes.add(rectangle);
            }
        }
    }
    public void addToPane(Pane parentPane) {
        for (Shape shape : shapes) {
            parentPane.getChildren().add(shape);
        }
    }

    public void removeFromPane(Pane parentPane) {
        for (Shape shape : shapes) {
            parentPane.getChildren().remove(shape);
        }
    }
}
