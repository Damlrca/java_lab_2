package org.example.java_lab;

import java.util.ArrayList;
import java.util.Iterator;

public class Lab2_Targets implements Iterable<Lab2_Target> {
    private ArrayList<Lab2_Target> targets = new ArrayList<>();

    public Lab2_Targets() {
        targets.add(new Lab2_Target(450, 250, 30, 200, 5, 1));
        targets.add(new Lab2_Target(550, 250, 15, 200, 10, 2));
    }

    public void nextTick() {
        for (Lab2_Target t : this) {
            t.nextTick();
        }
    }

    @Override
    public Iterator<Lab2_Target> iterator() {
        return targets.iterator();
    }
}
