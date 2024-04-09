package org.example.java_lab;

import javafx.scene.shape.Circle;

public class Lab2_Target {
    private Circle circle;
    private int cost;
    private int pos;
    private int speed;

    public Lab2_Target(Circle circle, int cost, int pos, int speed) {
        this.circle = circle;
        this.cost = cost;
        this.pos = pos;
        this.speed = speed;
    }

    public Circle getCircle() {
        return circle;
    }

    public int getCost() {
        return cost;
    }

    public void nextTick() {
        pos = (pos + speed) % 800;
        double Y;
        if (pos <= 400) {
            Y = 50 + pos;
        }
        else {
            Y = 50 + 800 - pos;
        }
        circle.setLayoutY(Y);
    }
}
