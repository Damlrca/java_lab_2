package org.example.java_lab;

import java.util.ArrayList;
import java.util.Iterator;

public class DAO implements Iterable<Lab_2_Point> {
    private ArrayList<Lab_2_Point> allPoints = new ArrayList<>();

    public void add(Lab_2_Point x) {
        allPoints.add(x);
    }

    public void set(ArrayList<Lab_2_Point> allPoints) {
        this.allPoints = allPoints;
    }

    public ArrayList<Lab_2_Point> getPoints() {
        return allPoints;
    }

    public void remove(Lab_2_Point x) {
        allPoints.remove(x);
    }

    @Override
    public Iterator<Lab_2_Point> iterator() {
        return allPoints.iterator();
    }
}
