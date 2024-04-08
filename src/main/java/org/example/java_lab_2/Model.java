package org.example.java_lab_2;

import java.util.ArrayList;
import java.util.Iterator;

public class Model implements Iterable<Point> {
    DAO dao = new DAO();
    ArrayList<IObserver> allObs = new ArrayList<>();

    public void event() {
        for (IObserver o : allObs) {
            o.event(this);
        }
    }

    public void addObserver(IObserver o) {
        allObs.add(o);
    }

    public void set(ArrayList<Point> allPoints) {
        dao.set(allPoints);
        event();
    }

    public ArrayList<Point> getPoints() {
        return dao.getPoints();
    }

    public void add(Point x) {
        dao.add(x);
        event();
    }

    public void remove(Point x) {
        dao.remove(x);
        event();
    }

    @Override
    public Iterator<Point> iterator() {
        return dao.iterator();
    }
}
