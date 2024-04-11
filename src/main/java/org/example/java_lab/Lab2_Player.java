package org.example.java_lab;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;

public class Lab2_Player {
    private String playerName;
    private int countScore = 0;
    private int countBullets = 0;
    private double playerX, playerY;
    private ArrayList<Point> bullets = new ArrayList<>();
    private String playerColor;
    private Lab2_PlayerStatus status = Lab2_PlayerStatus.PAUSE;

    public Lab2_Player(String playerName, double playerX, double playerY, String playerColor) {
        this.playerName = playerName;
        this.playerX = playerX;
        this.playerY = playerY;
        this.playerColor = playerColor;
    }

    public void nextTick(Lab2_Targets targets) {
        ArrayList<Point> bullets_to_remove = new ArrayList<>();
        for (Point bullet : bullets) {
            bullet.setX(bullet.getX() + 10);
            for (Lab2_Target t : targets) {
                double dx = t.getPosX() - bullet.getX();
                double dy = t.getPosY() - bullet.getY();
                double d2 = dx * dx + dy * dy;
                if (d2 <= t.getRadius() * t.getRadius()) {
                    countScore += t.getCost();
                    bullets_to_remove.add(bullet);
                }
            }
            if (bullet.getX() > 625) {
                bullets_to_remove.add(bullet);
            }
        }
        for (Point b : bullets_to_remove) {
            bullets.remove(b);
        }
    }

    public void newBullet() {
        bullets.add(new Point(playerX, playerY));
        countBullets++;
    }

    public void resetPlayer() {
        countScore = 0;
        countBullets = 0;
        bullets.clear();
        status = Lab2_PlayerStatus.PAUSE;
    }

    public String getPlayerName() {
        return playerName;
    }

    public int getCountScore() {
        return countScore;
    }

    public int getCountBullets() {
        return countBullets;
    }

    public double getPlayerX() {
        return playerX;
    }

    public double getPlayerY() {
        return playerY;
    }

    public ArrayList<Point> getBullets() {
        return bullets;
    }

    public String getPlayerColor() {
        return playerColor;
    }

    public Lab2_PlayerStatus getStatus() {
        return status;
    }

    public void setStatus(Lab2_PlayerStatus status) {
        this.status = status;
    }
}
