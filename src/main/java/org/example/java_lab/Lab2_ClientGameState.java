package org.example.java_lab;

import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.*;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Shape;

import java.util.ArrayList;

public class Lab2_ClientGameState {
    private static final RadialGradient targetGradient = new RadialGradient(0, 0, 0.5, 0.5, 0.15, true, CycleMethod.REPEAT, new Stop(0, Color.WHITE), new Stop(0.5, Color.RED), new Stop(1, Color.WHITE));
    private final ArrayList<Shape> shapes = new ArrayList<>();
    private String text;

    public Lab2_ClientGameState() {
    }

    void update(Lab2_GameState gameState) {
        shapes.clear();
        for (Lab2_Target target : gameState.getTargets()) {
            Circle circle = new Circle();
            circle.setLayoutX(target.getPosX());
            circle.setLayoutY(target.getPosY());
            circle.setRadius(target.getRadius());
            circle.setFill(targetGradient);
            circle.setStroke(Color.BLACK);
            shapes.add(circle);
        }
        for (Lab2_Player player : gameState.getPlayers()) {
            Polygon polygon = new Polygon(-25.0, -45.0, 0.0, 0.0, -25.0, 45.0);
            polygon.setFill(Paint.valueOf(player.getPlayerColor()));
            polygon.setStroke(Color.BLACK);
            polygon.setLayoutX(player.getPlayerX());
            polygon.setLayoutY(player.getPlayerY());
            shapes.add(polygon);
            for (Lab2_Point bullet : player.getBullets()) {
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
        text = gameState.getText();
    }

    public void addToPane(Pane parentPane, Label textLabel) {
        for (Shape shape : shapes) {
            parentPane.getChildren().add(shape);
        }
        textLabel.setText(text);
    }

    public void removeFromPane(Pane parentPane) {
        for (Shape shape : shapes) {
            parentPane.getChildren().remove(shape);
        }
    }
}
