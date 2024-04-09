package org.example.java_lab;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
    private Color player_color;

    public Lab2_Player(String player_name, double player_x, double player_y, Pane parent_pane, Color player_color) {
        this.player_name = player_name;
        this.player_x = player_x;
        this.player_y = player_y;
        this.parent_pane = parent_pane;
        this.player_color = player_color;
        // TODO player_triangle
        player_triangle = new Polygon(-25.0, -50.0, 0.0, 0.0, -25.0, 50.0);
        player_triangle.setFill(player_color);
        player_triangle.setStroke(Color.BLACK);
        player_triangle.setLayoutX(player_x);
        player_triangle.setLayoutY(player_y);
        parent_pane.getChildren().add(player_triangle);
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
                double dy = c.getLayoutY() - by;
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
        bullet.setLayoutX(player_x - 5.0);
        bullet.setLayoutY(player_y - 5.0);
        bullet.setFill(player_color);
        bullet.setStroke(Color.BLACK);
        bullet.setStrokeType(StrokeType.INSIDE);
        parent_pane.getChildren().add(bullet);
        bullets.add(bullet);
        count_bullets++;
    }

    public void deletePlayer() {
        removeBullets(bullets);
        // TODO player_triangle
        parent_pane.getChildren().remove(player_triangle);
    }

    public void removeBullets(ArrayList<Rectangle> bullets_to_remove) {
        for (Rectangle b : bullets_to_remove) {
            bullets.remove(b);
            parent_pane.getChildren().remove(b);
        }
    }
}
