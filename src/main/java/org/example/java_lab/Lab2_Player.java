package org.example.java_lab;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Polygon;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.StrokeType;

import java.util.ArrayList;

public class Lab2_Player {
    private String player_name;
    private int count_score = 0;
    private int count_bullets = 0;
    private double player_x = 0, player_y = 0;
    private ArrayList<Rectangle> bullets = new ArrayList<>();
    private Pane parent_pane;
    private Polygon player_triangle;

    public Lab2_Player(String player_name, double player_x, double player_y, Pane parent_pane) {
        this.player_name = player_name;
        this.player_x = player_x;
        this.player_y = player_y;
        this.parent_pane = parent_pane;
        // TODO player_triangle
    }

    public void nextTick(Lab2_Targets targets) {
        ArrayList<Rectangle> bullets_to_remove = new ArrayList<>();
        for (Rectangle b : bullets) {
            b.setLayoutX(b.getLayoutX() + 10);
            for (Lab2_Target t : targets) {
                Circle c = t.getCircle();
                double bx = b.getLayoutX() + b.getWidth() / 2;
                double by = b.getLayoutY() + b.getHeight() / 2;
                double dx = c.getLayoutX() - bx;
                double dy = c.getCenterY() - by;
                double d2 = dx * dx + dy * dy;
                if (d2 <= c.getRadius() * c.getRadius()) {
                    count_score += t.getCost();
                    bullets_to_remove.add(b);
                }
            }
            if (b.getLayoutX() > 625) {
                bullets_to_remove.add(b);
            }
        }
        removeBullets(bullets_to_remove);
    }

    public void newBullet() {
        Rectangle bullet = new Rectangle();
        bullet.setArcHeight(5.0);
        bullet.setArcWidth(5.0);
        bullet.setHeight(10.0);
        bullet.setWidth(10.0);
        bullet.setLayoutX(player_x);
        bullet.setLayoutY(player_y);
        bullet.setFill(Paint.valueOf("#28d622"));
        bullet.setStroke(Paint.valueOf("BLACK"));
        bullet.setStrokeType(StrokeType.INSIDE);
        parent_pane.getChildren().add(bullet);
        count_bullets++;
    }

    public void deletePlayer() {
        // TODO player_triangle
        removeBullets(bullets);
    }

    public void removeBullets(ArrayList<Rectangle> bullets_to_remove) {
        for (Rectangle b : bullets_to_remove) {
            bullets.remove(b);
            parent_pane.getChildren().remove(b);
        }
    }
}
