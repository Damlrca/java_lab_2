package org.example.java_lab;

import java.util.ArrayList;

public class Msg {
    private final ArrayList<Point> points;
    private final MsgAction action;

    public Msg(ArrayList<Point> p, MsgAction action) {
        this.points = p;
        this.action = action;
    }

    public ArrayList<Point> getPoints() {
        return points;
    }

    public MsgAction getAction() {
        return action;
    }

    @Override
    public String toString() {
        return "Msg{" +
                "points=" + points +
                ", action=" + action +
                '}';
    }
}
