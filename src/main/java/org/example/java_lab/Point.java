package org.example.java_lab;

import javax.persistence.*;

@Entity
@Table
public class Point {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    int id;
    @Column
    int x = 0, y = 0;

    public Point() {
    }

    public Point(double x, double y) {
        this.x = (int) x;
        this.y = (int) y;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = (int) x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = (int) y;
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
