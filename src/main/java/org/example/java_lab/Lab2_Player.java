package org.example.java_lab;

import java.util.ArrayList;

public class Lab2_Player {
    private int count_score = 0;
    private int count_bullets = 0;
    private float player_x = 0, player_y = 0;
    ArrayList<Lab2_Bullet> bullets;

    public Lab2_Player(float player_x, float player_y) {
        this.player_x = player_x;
        this.player_y = player_y;
    }

    public void nextTick() {
        for (Lab2_Bullet b : bullets) {

        }
    }

    public void newBullet() {

    }
}
