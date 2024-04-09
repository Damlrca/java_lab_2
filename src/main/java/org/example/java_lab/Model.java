package org.example.java_lab;

import java.util.ArrayList;
import java.util.Iterator;

public class Model implements Iterable<Lab_2_Point> {
    private DAO dao = new DAO();
    private ArrayList<IObserver> allObs = new ArrayList<>();

    public void event() {
        for (IObserver o : allObs) {
            o.event(this);
        }
    }

    public void addObserver(IObserver o) {
        allObs.add(o);
    }

    public void set(ArrayList<Lab_2_Point> allPoints) {
        dao.set(allPoints);
        event();
    }

    public ArrayList<Lab_2_Point> getPoints() {
        return dao.getPoints();
    }

    public void add(Lab_2_Point x) {
        dao.add(x);
        event();
    }

    public void add(ArrayList<Lab_2_Point> x) {
        for (Lab_2_Point p : x) {
            dao.add(p);
        }
        event();
    }

    public void remove(Lab_2_Point x) {
        dao.remove(x);
        event();
    }

    @Override
    public Iterator<Lab_2_Point> iterator() {
        return dao.iterator();
    }
}
