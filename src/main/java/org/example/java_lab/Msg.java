package org.example.java_lab;

import java.util.ArrayList;

public class Msg {
    private ArrayList<Lab_2_Point> points;
    private MsgAction action;

    public Msg(ArrayList<Lab_2_Point> p, MsgAction action) {
        this.points = p;
        this.action = action;
    }

    public ArrayList<Lab_2_Point> getPoints() {
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
