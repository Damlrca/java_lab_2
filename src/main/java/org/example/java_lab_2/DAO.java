package org.example.java_lab_2;

import java.util.ArrayList;
import java.util.Iterator;

public class DAO implements Iterable<Point> {
    ArrayList<Point> allPoints = new ArrayList<>();

    public void add(Point x) {
        allPoints.add(x);
    }

    public void set(ArrayList<Point> allPoints) {
        this.allPoints = allPoints;
    }

    public ArrayList<Point> getPoints() {
        return allPoints;
    }
    public void remove(Point x) {
        allPoints.remove(x);
    }

    @Override
    public Iterator<Point> iterator() {
        return allPoints.iterator();
    }
}
