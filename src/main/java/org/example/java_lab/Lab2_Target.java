package org.example.java_lab;

public class Lab2_Target {
    private final double posX;
    private double posY;
    private final double radius;
    private int pos;
    private final int speed;
    private final int cost;

    public Lab2_Target(double posX, double posY, double radius, int pos, int speed, int cost) {
        this.posX = posX;
        this.posY = posY;
        this.radius = radius;
        this.pos = pos;
        this.speed = speed;
        this.cost = cost;
    }

    public void nextTick() {
        pos = (pos + speed) % 800;
        if (pos <= 400) {
            posY = 50 + pos;
        } else {
            posY = 50 + 800 - pos;
        }
    }

    public double getPosX() {
        return posX;
    }

    public double getPosY() {
        return posY;
    }

    public double getRadius() {
        return radius;
    }

    public int getCost() {
        return cost;
    }

}
